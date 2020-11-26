package com.wintig.rpc.remote.vo;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String userName;

    private String phone;

    public UserInfo(String userName, String phone) {
        this.userName = userName;
        this.phone = phone;
    }

    public UserInfo() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
