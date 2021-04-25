package com.distribution.lock.test;

/**
 * @description: 条件节点
 * @author: wx
 * @create: 2021-03-26 17:35
 **/
public class ConditionNode {

    private Node conditionTrue;
    private Node conditionFalse;
    private boolean conditionFlag;

    public ConditionNode() {
    }

    public ConditionNode setConditionTrue(Node conditionTrue) {
        this.conditionTrue = conditionTrue;
        return this;
    }

    public ConditionNode setConditionFalse(Node conditionFalse) {
        this.conditionFalse = conditionFalse;
        return this;
    }

    public ConditionNode setConditionFlag(boolean conditionFlag) {
        this.conditionFlag = conditionFlag;
        return this;
    }

    public void execute(){
        if (conditionFlag){
            conditionTrue.execute();
        } else {
            conditionFalse.execute();
        }
    }
}
