package com.wintig.bio.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerPool {

    
    private static ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(10001));

        System.out.println("服务器已启动...");

        while (true) {
            executorService.execute(new ServerTask(serverSocket.accept()));
        }

    }

    private static class ServerTask implements Runnable {

        private Socket socket = null;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

                String userName = inputStream.readUTF();
                System.out.println("客户端收到信息：" + userName);

                // 这里只是暂时放到操作系统缓存中
                outputStream.writeUTF("Hello, " + userName);

                // 强制刷新
                outputStream.flush();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (Exception e) {

                }
            }
        }
    }

}
