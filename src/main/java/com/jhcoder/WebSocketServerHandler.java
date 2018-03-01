package com.jhcoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class WebSocketServerHandler extends SocketServerHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        byte[] bytes = new byte[1024];
        byteBuf.readBytes(bytes);
        System.out.println(String.valueOf(bytes));
        System.out.println("aaa");
        //System.out.println(bytes.toString());
    }
}
