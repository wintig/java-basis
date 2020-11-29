package com.wintig.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    // 表示读取到网络数据以后，进行业务处理
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {

        System.out.println("客户端收到服务器数据：" + msg.toString(CharsetUtil.UTF_8));

    }

    // channel活跃后，做业务处理
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, Netty!", CharsetUtil.UTF_8));
    }
}
