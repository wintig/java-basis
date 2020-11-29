package com.wintig.nio.nio;
import java.io.IOException;
import java.util.Scanner;

import static com.wintig.nio.Const.DEFAULT_PORT;
import static com.wintig.nio.Const.DEFAULT_SERVER_IP;

public class NioClient {

    private static NioClientHandle nioClientHandle;

    private static void start() throws IOException {
        nioClientHandle = new NioClientHandle(DEFAULT_SERVER_IP, DEFAULT_PORT);
    }

    public static boolean sendMsg(String msg) throws Exception {
        nioClientHandle.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) throws Exception {
        start();
        Scanner scanner = new Scanner(System.in);
        while (NioClient.sendMsg(scanner.next()));
    }

}
