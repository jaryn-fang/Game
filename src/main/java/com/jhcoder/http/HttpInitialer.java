package com.jhcoder.http;

import com.jhcoder.http.HttpHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpInitialer  extends ChannelInitializer<SocketChannel>{
    private HttpHandler handler;
    private final SslContext sslCtx;

    public HttpInitialer(SslContext sslCtx, HttpHandler handler) {
        this.handler = handler;
        this.sslCtx = sslCtx;
    }

    /**
     * @param ch
     * @throws Exception
     * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // CorsConfig corsConfig = CorsConfigBuilder.forAnyOrigin().allowNullOrigin().allowCredentials().build();
        ChannelPipeline pipeline = ch.pipeline();
        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpRequestDecoder());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new ChunkedWriteHandler());
        // pipeline.addLast(new CorsHandler(corsConfig));
        pipeline.addLast(handler.getClass().newInstance());
    }
}
