package com.jhcoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseNettyClient {

    private String ip;
    private int port;
    protected EventLoopGroup workerGroup;
    protected final List<Channel> chs = new ArrayList<Channel>();


    public BaseNettyClient(final String name, String ip, int port, int workThreadCount, ChannelInitializer<SocketChannel> webSocketServerInitializer) {
        this.workerGroup = new NioEventLoopGroup(1, new BaseThreadFactory("ClientThread"));
        try {
            this.ip = ip;
            this.port = port;
            Bootstrap b = new Bootstrap();
            b.group(workerGroup).channel(NioSocketChannel.class).handler(webSocketServerInitializer);
            //initParam(b);
            for (int i = 0; i < workThreadCount; i++) {
                chs.add(b.connect(this.ip, this.port).sync().channel());
            }
            System.out.println("连接服务器成功，ip：" + ip + ",port:" + port);
        } catch (Throwable e) {
            System.out.println("Netty客户端启动异常");
        }
    }

    public final static BaseNettyClient createInstance(Class<?> clazz, String name, String host, int port, int workThreadCount, ChannelInitializer<SocketChannel> serverInitializer) {
        try {
            Constructor<?> con = clazz.getConstructor(String.class, String.class, int.class, int.class, ChannelInitializer.class);
            return (BaseNettyClient) con.newInstance(name, host, port, workThreadCount, serverInitializer);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract void initParam(Bootstrap boot);

    public List<Channel> getChs() {
        return chs;
    }
}
