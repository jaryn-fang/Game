package com.jhcoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.lang.reflect.Constructor;

public abstract class BaseNettyServer {
    private Channel channel;
    private boolean start;
    private EventLoopGroup boosGroup;
    private EventLoopGroup workGroup;
    // private AtomicInteger idx = new AtomicInteger();

    public BaseNettyServer(int port, int workThreadCount, ChannelInitializer channelInitializer) {
        final String name = this.getClass().getName();
        boosGroup = new NioEventLoopGroup(1, new BaseThreadFactory("boosGroup"));
        workGroup = new NioEventLoopGroup(1, new BaseThreadFactory("workGroup"));
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(boosGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(channelInitializer);
            channel = boot.bind(port).sync().channel();
            start = true;
            System.out.println("绑定了端口" + port);
        } catch (Throwable e) {

        }
    }

    public static BaseNettyServer createInNettyServer(Class<SocketServer> clazz, int port, int workThreadCount, ChannelInitializer channelInitializer) {
        try {
            Constructor<?> con = clazz.getConstructor(int.class, int.class, ChannelInitializer.class);
            return (BaseNettyServer) con.newInstance(port, workThreadCount, channelInitializer);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    protected  abstract  void initparam(ServerBootstrap boot);


}
