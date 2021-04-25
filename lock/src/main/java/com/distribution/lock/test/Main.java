package com.distribution.lock.test;

/**
 * @description: 测试
 * @author: wx
 * @create: 2021-03-26 18:19
 **/
public class Main {

    public static void main(String[] agrs) {
        Test test = new Test();
        Node nodeA = new Node(test, "a").setArgs(new Integer[]{1, 2});
        Node nodeB = new Node(test,"b").setArgs(new Integer[]{1});
        Node nodeC = new Node(test,"c");
        Node nodeD = new Node(test,"d").setArgs(new String[]{"我是D"});

        //节点D下个节点是C,并且会把返回值传递给C执行方法
        nodeD.setNext(nodeC).setSetArgsFlag(true);

        //构建条件节点，true执行nodeB  false执行nodeD
        ConditionNode conditionNode = new ConditionNode()
                .setConditionTrue(nodeD)
                .setConditionFalse(nodeB);

        //A连接条件节点conditionNode
        nodeA.setNext(conditionNode);

        //执行方法
        nodeA.execute();




    }
}
