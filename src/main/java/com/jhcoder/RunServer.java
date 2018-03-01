package com.jhcoder;

public class RunServer {
    public static void main(String[] args) {
        BaseNettyServer socketServer =  BaseNettyServer.createInNettyServer(SocketServer.class, 8071, 4, new WebSocketServerInitializer(new WebSocketServerHandler()));
        //BaseNettyServer.createInNettyServer(HttpServer.class, 8888, 4, new HttpInitialer(null, new HttpHandler()));
    }
}
