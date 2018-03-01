package com.jhcoder;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunClient {
    public static void main(String[] args) {
        BaseNettyClient client =  BaseNettyClient.createInstance(SocketClient.class, "client","127.0.0.1", 8071,1, new WebSocketClientInitializer(new WebSocketClientHandler()));
        List<Channel> chs =  client.getChs();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new SendThreah(chs), 10, 10 , TimeUnit.SECONDS);
    }
}

class SendThreah implements  Runnable {

    private  List<Channel> chs = new ArrayList<Channel>();

    public SendThreah(List<Channel> chs) {
        this.chs = chs;
    }

    public void run() {
        if(chs.size()!=0) {
            for(Channel ch : chs) {
                if(ch.isOpen() && ch.isActive()) {
                    System.out.println("client测试输出");
                    ch.writeAndFlush("client测试输出");
                }
            }
        }
    }
}
