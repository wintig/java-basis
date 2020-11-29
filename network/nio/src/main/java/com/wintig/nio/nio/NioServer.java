package com.wintig.nio.nio;

import static com.wintig.nio.Const.DEFAULT_PORT;

public class NioServer {

    private static NioServerHandle nioServerHandle;

    public static void main(String[] args) {
        nioServerHandle = new NioServerHandle(DEFAULT_PORT);
        new Thread(nioServerHandle, "Server").start();
    }



}
