package com.wintig.bio.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 10001);
        try {
            socket.connect(addr);

             outputStream = new ObjectOutputStream(socket.getOutputStream());
             inputStream = new ObjectInputStream(socket.getInputStream());

            outputStream.writeUTF("wintig");
            outputStream.flush();

            // 接受服务器的输出u
            System.out.println(inputStream.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
            if (outputStream != null) outputStream.close();
            if (inputStream != null) inputStream.close();
        }
    }

}
