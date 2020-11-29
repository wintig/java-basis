package com.wintig.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {

    private final int port;
    private final String host;

    public EchoClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() throws InterruptedException {


        // 线程组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // 客户端启动必备
            Bootstrap bootstrap = new Bootstrap();

            // 表示当前netty服务器使用当前线程组
            bootstrap.group(group)
                    .channel(NioSocketChannel.class) //指定使用nio的通讯模式
                    .remoteAddress(new InetSocketAddress(host, port))  // 指定服务器的ip和端口

                    // 初始化时间处理器
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });


            // 异步连接到服务器，sync会阻塞到完成
            ChannelFuture channelFuture = bootstrap.connect().sync();

            // 阻塞当前线程，直到服务器的channel被关闭
            channelFuture.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient(9999, "127.0.0.1").start();
    }

}
