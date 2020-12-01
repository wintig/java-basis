package com.wintig.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 9999;
        EchoServer server = new EchoServer(port);
        System.out.println("服务器正在启动...");
        server.start();
        System.out.println("服务器已关闭...");
    }

    public void start() throws InterruptedException {

        final EchoServerHandler serverHandler = new EchoServerHandler();

        // 线程组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // 服务端启动必备
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 表示当前netty服务器使用当前线程组
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class) //指定使用nio的通讯模式
                    .localAddress(new InetSocketAddress(port))  // 指定监听端口

                    // 初始化事件处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });


            // 异步绑定到服务器，sync会阻塞到完成
            ChannelFuture channelFuture = bootstrap.bind().sync();

            // 阻塞当前线程，直到服务器的channel被关闭
            channelFuture.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync();
        }

    }

}
