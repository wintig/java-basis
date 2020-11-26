package com.wintig.rpc.remote;

import com.wintig.rpc.remote.vo.UserInfo;

public interface SendSMS {

    boolean sendMail(UserInfo userInfo);

}
