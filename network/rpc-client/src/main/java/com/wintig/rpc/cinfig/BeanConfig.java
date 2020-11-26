package com.wintig.rpc.cinfig;

import com.wintig.rpc.remote.SendSMS;
import com.wintig.rpc.rpc.RpcClientFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Autowired
    private RpcClientFrame rpcClientFrame;

    @Bean
    public SendSMS getSmeService() {
        // 产生的代理对象
        return rpcClientFrame.getRemoteProxyObject(SendSMS.class);
    }

}
