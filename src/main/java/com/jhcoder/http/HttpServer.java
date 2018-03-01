package com.jhcoder.http;

import com.jhcoder.BaseNettyServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;

public class HttpServer extends BaseNettyServer {

    public HttpServer(int port, int workThreadCount, ChannelInitializer channelInitializer) {
        super(port, workThreadCount, channelInitializer);
    }

    protected void initparam(ServerBootstrap boot) {
        // 不管数据包大小，不组合包，直接发送
        boot.childOption(ChannelOption.TCP_NODELAY, true);
        // 默认使用内存池分配ByteBuf
        boot.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        // 启用接收区缓存自动调节大小策略
        boot.option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT);
    }
}
