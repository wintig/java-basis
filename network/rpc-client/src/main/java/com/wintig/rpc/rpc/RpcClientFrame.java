package com.wintig.rpc.rpc;

import org.springframework.stereotype.Service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

@Service
public class RpcClientFrame {

    public static<T> T getRemoteProxyObject(final Class<?> serviceInterface) {

        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8778);

        return (T)Proxy.newProxyInstance(
                serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface},
                new DynProxy(serviceInterface, addr)
                );
    }

    // 实现远程对象的访问
    private static class DynProxy implements InvocationHandler {

        private Class<?> serviceInterface;
        private InetSocketAddress addr;

        public DynProxy(Class<?> serviceInterface, InetSocketAddress addr) {
            this.serviceInterface = serviceInterface;
            this.addr = addr;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;
            ObjectOutputStream output = null;
            ObjectInputStream input = null;

            try {
                socket = new Socket();
                socket.connect(addr);

                output = new ObjectOutputStream(socket.getOutputStream());


                // 方法所在类名，接口名
                output.writeUTF(serviceInterface.getName());

                // 方法的名字
                output.writeUTF(method.getName());

                // 方法的入参类型
                output.writeObject(method.getParameterTypes());

                // 方法入参的值
                output.writeObject(args);

                output.flush();

                input = new ObjectInputStream(socket.getInputStream());
                // 接受服务端的输出
                System.out.println(serviceInterface + " 执行成功");
                return input.readObject();
            } finally {
                if (socket != null) socket.close();
                if (output != null) output.close();
                if (input != null) input.close();
            }
        }
    }

}
