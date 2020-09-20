package CAS.ABA;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ABA问题 {

    private static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);

    /**
     * 在你非常渴的情况下你发现一个盛满水的杯子，你一饮而尽，之后再给杯子里重新倒满水。
     * 然后你离开，当杯子的真正主人回来时看到杯子还是盛满水，他当然不知道是否被人喝完重新倒满。
     *
     * 解决这个问题的方案的一个策略是每一次倒水假设有一个自动记录仪记录下，这样主人回来就可以分辨在她离开后是否发生过重新倒满的情况这也是解决ABA问题目前采用的策略。
     *
     * @param args
     */
    public static void main(String[] args) {

        // 1- 初始值为100，线程t1将100改成101，然后又将101改回100
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        },"t1").start();

        // 2- 线程t2先睡眠1秒，等待t1操作完成，然后t2线程将值改成2019
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 可以看到，线程2修改成功
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t修改后的值:" + atomicReference.get());
        },"t2").start();
    }



}
