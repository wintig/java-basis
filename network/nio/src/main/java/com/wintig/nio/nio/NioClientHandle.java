package com.wintig.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class NioClientHandle implements Runnable{

    private String host;
    private int port;
    private volatile boolean started;

    private Selector selector;
    private SocketChannel socketChannel;

    public NioClientHandle(String ip, int port) throws IOException {
        this.host = ip;
        this.port = port;

        // 创建选择器实例
        selector = Selector.open();

        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);   // 设置通道为非阻塞模式



        started = true;
        System.out.println("服务器已启动，端口号：" + port);
    }

    public void stop() {
        started = false;
    }


    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void doConnect() throws IOException {

        // 非阻塞的连接
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            // 连接建立成功
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {

            // 如果建立失败，那么就注册一个事件，你建立好了再来通知我
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

    }

    public void sendMsg(String msg) throws Exception {
        doWrite(socketChannel, msg);
    }

    private void doWrite(SocketChannel socketChannel, String request) throws IOException {
        // 将消息编码为字节数组
        byte[] bytes = request.getBytes();
        // 根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        // 将字节数组复制到缓冲区
        writeBuffer.put(bytes);

        writeBuffer.flip();

        // 发送缓冲区的字节数组
        socketChannel.write(writeBuffer);
    }
}
