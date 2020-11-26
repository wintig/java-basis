package com.wintig.rpc.rpc.sms;

import com.wintig.rpc.remote.SendSMS;
import com.wintig.rpc.rpc.base.RpcServerFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Random;

@Service
public class SmsRpcServer {

    @Autowired
    private RpcServerFrame rpcServerFrame;

    @PostConstruct
    public void server() throws IOException {

        Random r = new Random();
        int port = 8778;

        rpcServerFrame.startService(SendSMS.class.getName(),
                "127.0.0.1", port, SendSmsImpl.class);

    }

}
