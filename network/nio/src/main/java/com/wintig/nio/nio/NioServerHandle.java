package com.wintig.nio.nio;

import com.wintig.nio.Const;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio通信服务端处理器
 */
public class NioServerHandle implements Runnable{

    private volatile boolean started;

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public NioServerHandle(int port) {

        try {
            // 创建选择器实例
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);   // 设置通道为非阻塞模式
            serverSocketChannel.socket().bind(new InetSocketAddress(port)); // 绑定端口

            started = true;
            System.out.println("服务器已启动，端口号：" + port);

            // 注册事件，表示关心客户端连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (started) {

            try {
                // selector.selectNow() 立即返回
                // 是一个阻塞试的方法，每隔1秒选择一次，获取事件
                selector.select(1000);

                // 如果有事件产生，则返回一个事件集
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();  // 从事件集移除
                    handleInput(key);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 处理事件的发生
    private void handleInput(SelectionKey key) throws IOException {

        // key是不是有效
        if (!key.isValid()) {
            return;
        }

        // 事件是一个连接事件，处理客户端的请求
        if (key.isAcceptable()) {

            // 获取当前关心事件的channel
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            // 建立三次握手
            SocketChannel socketChannel = ssc.accept();
            System.out.println("连接已建立");

            ssc.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ); // 关注读事件
        }

        // 处理对端发送的数据
        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 从通道里面读取数据，然后写入buffer里面，表示读到数据的数量
            int readBytes = socketChannel.read(buffer);
            if (readBytes > 0) {

                buffer.flip();

                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);  // 从buffer中获取相关数据，写入bytes

                String message = new String(bytes, "UTF-8");
                System.out.println("服务器收到：" + message);

                String result = Const.response(message);
                // 写给客户端
                doWrite(socketChannel, result);

            }
            // 表示对端已经没有发送数据了
            else if (readBytes < 0) {
                key.cancel();   // 关闭通道
                socketChannel.close();
            }
        }


    }

    private void doWrite(SocketChannel socketChannel, String response) throws IOException {

        byte[] responseBytes = response.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(responseBytes.length);

        buffer.put(responseBytes);
        buffer.flip();
        socketChannel.write(buffer);
    }

    public void stop() {
        started = false;
    }
}
