package yield;

public class YieldTest {

    public static void main(String[] args) {

        /**
         * 让出CPU的。让出CPU的意思并不是当前线程不执行了。
         *
         * 当前线程在让出CPU后，还会进行CPU资源争夺，但是是否能立马被分配到就不一定了。
         * 相当于是，我已经完成了一些重要的工作了，我可以休息一下，可以给其他线程一些工作机会。
         *
         * 如果你觉得一个线程不那么重要，或者优先级非常低，而且又害怕它会占用太多CPU资源，可以调用Thread.yield方法
         * 给其他线程更多的工作机会
         */

    }

}
