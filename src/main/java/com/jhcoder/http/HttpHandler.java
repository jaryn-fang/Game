package com.jhcoder.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http2.Http2Connection;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpHandler extends SimpleChannelInboundHandler<Object>{

    private static final byte[] CONTENT = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'};
    private boolean keepAlive;

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
    }

}
