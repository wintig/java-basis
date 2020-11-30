package com.wintig.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static com.wintig.nio.Const.*;

public class NioServerHandleWriteable implements Runnable{
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean started;

    public NioServerHandleWriteable(int port) {
        try{
            selector = Selector.open();

            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);//开启非阻塞模式
            serverChannel.socket().bind(new InetSocketAddress(port),1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            started = true;
            System.out.println("服务器已启动，端口号：" + port);
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        while(started){
            try{
                //阻塞,只有当至少一个注册的事件发生的时候才会继续.
				selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while(it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{
                        handleInput(key);
                    }catch(Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
        //selector关闭后会自动释放里面管理的资源
        if(selector != null) {
            try{
                selector.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void handleInput(SelectionKey key) throws IOException{
        System.out.println("当前通道的事件："+ key.interestOps());
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                System.out.println("======socket channel 建立连接=======");
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            //读消息
            if(key.isReadable()){
                System.out.println("======socket channel 数据准备完成，" + "可以去读==读取=======");
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(buffer);
                if(readBytes>0){
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String message = new String(bytes,"UTF-8");
                    System.out.println("服务器收到消息：" + message);
                    String result = response(message) ;
                    doWrite(sc,result);
                }
                else if(readBytes<0){
                    key.cancel();
                    sc.close();
                }
            }
            if (key.isWritable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer buffer = (ByteBuffer)key.attachment();
                if(buffer.hasRemaining()){
                    int count = sc.write(buffer);
                    System.out.println("写了 : "+count + "byte, 剩余 : "+buffer.hasRemaining());
                } else {
                    // 如果没有内存写了，就取消对写的注册
                    // 当你需要写的数据非常大的时候：比如你要写8M的数据，但是你现在的操作缓冲只有24k，一下子肯定写不进我只能拆成很多分的24k
                    // 当你发完了，我写一点发一点写一点；
                    key.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }

    //发送应答消息
    private void doWrite(SocketChannel channel,String response)
            throws IOException {
        byte[] bytes = response.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();

        // TODO 注册一个写事件，防止后面的事件把前面的事件冲掉，表示既关注写也关注读
        // TODO 同时需要把当前的buffer挂到channel里面
        channel.register(selector,SelectionKey.OP_WRITE|SelectionKey.OP_READ, writeBuffer);
    }

    public void stop(){
        started = false;
    }

}
