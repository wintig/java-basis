package com.wintig.nio;

import java.util.Date;

public class Const {

    public static int DEFAULT_PORT = 12345;
    public static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static String response(String msg) {
        return "Hello," + msg + ", Now is " + new Date().toString();
    }

}
