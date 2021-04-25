package com.distribution.lock.test;

/**
 * @description:
 * @author: wx
 * @create: 2021-03-26 18:19
 **/
public class Test {

    public boolean a(int i,int j){
        System.out.println("执行了a方法--------------");
        return i > 0;
    }

    public int b(int i){
        System.out.println("执行了b方法--------------");
        return i;
    }

    public void c(String a){
        System.out.println("执行了c方法--------------");
        System.out.println(a);
    }

    public String d(String a){
        System.out.println("执行了d方法--------------");
        return a;
    }
}
