package Interrupt线程中断;

/**
 * Thread.interrupted();                      判断线程的中断状态，并清除中断状态
 * Thread.currentThread().isInterrupted();    返回当前线程的中断状态
 *
 * 这个和isInterrupt很相似，假设此时线程状态是true
 * interrupted再调用之后会将状态线程状态设置false未中断，而isInterrupt不会改变中断状态
 *
 */
public class InterruptedAndIsInterruptMain {


    /**
     * interrupted到底有什么用，我检查一下中断状态之后又改变了这个状态到底是为了什么
     *
     * 举个例子：
     * 你现在在睡觉定了一个闹钟。闹钟响了，把你从睡梦中惊醒了之后，这个时候，你一定会将闹钟的状态取消掉。
     *
     * 在这里也是如此，第一次调用的时候，已经告诉你，是true了。证明你已经被人吵醒了，这个时候你已经是清醒状态了，这个事情已经过去了，线程还要正常运行，他的interrupted状态应该恢复成false
     * 因为你查看过一次这个状态属性，你没必要查看第二次吧，查看过一次之后，证明你已经知道了，后面不再需要这个属性值了。
     * 因为你很有可能会被别人重复多次调用interrupt，如果第一次的这个值不被清楚，后续再查看，我根本不清楚是哪一次被interrupt了。
     *
     * 那么这个功能到底有什么用，其实就是允许线程可以被中断多次，但是还是没什么卵用。
     * 我们完全可以使用两个线程共享的内存数据，来实现两个线程的通信而不必非要拘泥于这个interrupt上。
     *
     */

}
