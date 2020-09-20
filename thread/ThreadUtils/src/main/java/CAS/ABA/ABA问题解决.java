package CAS.ABA;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA问题解决 {

    /**
     * AtomicStampedReference
     * AtomicStampedReference内部维护了对象值和版本号，在创建AtomicStampedReference对象时，需要传入初始值和初始版本号，
     * 当AtomicStampedReference设置对象值时，对象值以及状态戳都必须满足期望值，写入才会成功
     */
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);


    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println("t1拿到的初始版本号:" + atomicStampedReference.getStamp());

            //睡眠1秒，是为了让t2线程也拿到同样的初始版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            atomicStampedReference.compareAndSet(101, 100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
        },"t1").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("t2拿到的初始版本号:" + stamp);

            //睡眠3秒，是为了让t1线程完成ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2最新版本号:" + atomicStampedReference.getStamp());
            System.out.println("t2修改结果：" + atomicStampedReference.compareAndSet(100, 2019,stamp,atomicStampedReference.getStamp() + 1));
            System.out.println("t2当前值:" + atomicStampedReference.getReference());
        },"t2").start();
    }

}
