package com.wintig.rpc.rpc.sms;

import com.wintig.rpc.remote.SendSMS;
import com.wintig.rpc.remote.vo.UserInfo;

public class SendSmsImpl implements SendSMS {
    @Override
    public boolean sendMail(UserInfo user) {

        System.out.println("已发送短息给：" + user.getUserName() + " 到" + user.getPhone());

        return true;
    }
}
