package com.distribution.lock.test;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @description: 节点对象
 * @author: wx
 * @create: 2021-03-26 17:30
 **/

public class Node {

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 目标方法
     */
    private String methodName;

    /**
     * 参数
     */
    private Object[] args;

    /**
     * 下个节点
     */
    private Object next;

    /**
     * 设置参数标记
     */
    private boolean setArgsFlag;

    public Node setArgs(Object[] args) {
        this.args = args;
        return this;
    }

    public Node setNext(Object next) {
        this.next = next;
        return this;
    }

    public Node setSetArgsFlag(boolean setArgsFlag) {
        this.setArgsFlag = setArgsFlag;
        return this;
    }

    public Node(Object target, String methodName) {
        this.target = target;
        this.methodName = methodName;
    }

    public void execute() {
        Object result = invoke();
        if (next instanceof Node) {
            Node nextNode = (Node) this.next;
            if (setArgsFlag) {
                //设置参数
                nextNode.setArgs(new Object[]{result});
            }
            nextNode.execute();

        } else if (next instanceof ConditionNode) {
            ConditionNode conditionNode = (ConditionNode) this.next;
            conditionNode.setConditionFlag(Boolean.parseBoolean(result.toString())).execute();
        }

    }

    //执行方法
    public Object invoke() {
        Object result = null;
        Method method = null;
        Method[] declaredMethods = target.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (Objects.equals(declaredMethod.getName(), methodName)) {
                method = declaredMethod;
                break;
            }
        }

        if (method == null) {
            throw new RuntimeException(String.format("%s方法不存在", methodName));
        }

        try {
            if (args != null) {
                result = method.invoke(target, args);
            } else {
                result = method.invoke(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
