package com.wintig.rpc.rpc.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class RpcServerFrame {

    @Autowired
    private RegisterService registerService;

    // 服务端端口
    private int port;

    // 接受客户端收到的请求
    private static class ServerTask implements Runnable {

        private Socket socket;
        private RegisterService registerService;

        public ServerTask(Socket client, RegisterService registerService) {
            this.socket = client;
            this.registerService = registerService;
        }

        @Override
        public void run() {
            try (
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {


                // 调用接口名
                String serviceName = inputStream.readUTF();

                // 方法的名字
                String methodName = inputStream.readUTF();

                // 入参类型
                Class<?>[] paramTypes = (Class<?>[])inputStream.readObject();

                // 方法值
                Object[] args = (Object[])inputStream.readObject();

                // 获取方法的class
                Class serviceClass = registerService.getLocalService(serviceName);
                if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + "服务不存在");
                }

                // 拿到方法对应的method对象
                Method method = serviceClass.getMethod(methodName, paramTypes);
                Object result = method.invoke(serviceClass.newInstance(), args);

                outputStream.writeObject(result);

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

    public void startService(String serviceName, String host, int port, Class impl) throws IOException {

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        registerService.registerService(serviceName, impl);
        System.out.println("RPC 服务 on : " + port + " 运行");

        try {
            while (true) {
                new Thread(new ServerTask(serverSocket.accept(), registerService)).start();
            }
        }finally {
            serverSocket.close();
        }

    }

}
