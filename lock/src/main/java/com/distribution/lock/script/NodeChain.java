package com.distribution.lock.script;

import lombok.Data;

@Data
public class NodeChain {

    // 方法key
    private String methodKey;

    // 方法入参，是个json
    private String params;

    // 前端渲染用的参数
    // private String editParams;

    // 当前node的返回值
    private Object result;

    // 下一个节点
    private NodeChain nextNodeChain;

}
