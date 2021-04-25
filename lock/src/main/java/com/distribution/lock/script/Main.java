package com.distribution.lock.script;

import com.distribution.lock.script.method.ActMethod;
import com.distribution.lock.script.method.handler.ActMethodHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    @Autowired
    private ActMethodHandler actMethodHandler;

    static Map<String,String>  map = new HashMap<>();

    public static void hashMapTest() {
        for (int i = 0;i < 500;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0;j < 500;j++) {
                        map.put(Thread.currentThread().getName() + "-" + j, j+"");
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
//            map.forEach((x,y) -> System.out.println(x));
            System.out.println(map.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        hashMapTest();
    }

    public void main1(String[] args) {



        List<NodeChain> nodeChains = new ArrayList<>();

        // 退出的条件是，chain执行完毕
        for (NodeChain nodeChain : nodeChains) {

            // 获取方法
            ActMethod method = actMethodHandler.getMethodByKey(nodeChain.getMethodKey());
            Object result = method.execute(nodeChain.getParams());


        }

    }

}
