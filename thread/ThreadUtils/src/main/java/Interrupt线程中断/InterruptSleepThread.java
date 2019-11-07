package Interrupt线程中断;

/**
 * 中断沉睡中的线程
 */
public class InterruptSleepThread {

    /**
     * 当中断一个sleep，wait，join的时候这时候会抛出一个异常。
     *
     * 很多时候，我们都没有考虑这里为什么要抛异常。
     * 想想一个场景，去上厕所就只有1个坑，门口站满了人，都在等那个人出来。
     * 每个人都不甘落后，都想第一个冲上去抢到坑位。你在那里等了1小时了也没有想到，实在竞争太激烈了。
     *
     * 这个时候你处于wait状态。但是你女朋友在外面等的不耐烦了，给你打了个电话叫你出来，这个打电话的动作就是interrupt来打断你的wait状态。
     * 如果没人interrupt你，你会一直wait下去。直到天荒地老，直到你抢到锁。
     *
     * 同理，join和sleep也有这样的情况，有一些线程处于join和sleep状态中，但是有一些时候，外部环境改变了，很有可能需要你退出这样的等待状态。
     * 本来sleep1小时很好的，但是这时候可能一些业务发生了变化需要你提前结束睡眠。
     * 所以interrupt专门就是为sleep，wait，join而生的
     *
     * 我们每次写sleep，wait，join的时候，每次都要搞个catch，你总感觉很烦很啰嗦。
     * 那是因为你的sleep，wait，join可能被人提前结束。提前结束后，很有可能你的线程不能再走正常的逻辑了（列如再去抢厕所）。而是走一个catch的逻辑，着很有可能是你的业务使然。
     * 但是即便如此，我们在sleep的时候catch到了，也是打印，然后接着执行正常醒来之后的代码逻辑。
     *
     * 我们在代码中都是这样做的，根本没有想过为什么会抛这个异常！
     *
     */
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("线程被中断了");
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

}
