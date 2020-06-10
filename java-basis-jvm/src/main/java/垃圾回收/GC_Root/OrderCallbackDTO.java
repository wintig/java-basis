package 垃圾回收.GC_Root;


public class OrderCallbackDTO {

    private String orderId;

    // 当前状态 5：已接单
    private Integer status;

    // 操作 status=5 ：1发货  2退款
    private Integer operating;


    // 额外参数
    private String ext;

    // 发货
    class Ok {

        // 地址
        private String add;

    }

    // 退款处理
    class Refund {

        // 原因
        private String reason;

    }

}
