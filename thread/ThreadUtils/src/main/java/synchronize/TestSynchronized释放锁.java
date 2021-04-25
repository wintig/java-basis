package synchronize;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

public class TestSynchronized释放锁 {

    static List<Thread> list = new ArrayList<>();
    static Object lock = new Object();

    public int[] getLeastNumbers(int[] arr, int k) {

        int[] vec = new int[k];
        if (k == 0) { // 排除 0 的情况
            return vec;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        });

        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            vec[i] = queue.poll();
        }
        return vec;
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " 正在执行...");
                    try {
                        //这里的睡眠没有什么意义，仅仅为了控制台打印的时候有个间隔 视觉效果好
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t" + i); //给每个线程去了一个名字 t1 t2 t3 ....
            list.add(t);
        }

        System.out.println("---启动顺序---");

        synchronized (lock) {
            for (Thread thread : list) {
                //这个打印主要是为了看到线程启动的顺序
                System.out.println(thread.getName() + "-启动");

                thread.start();

                // 这个睡眠相当重要，如果没有这个睡眠会有很大问题
                // 这里是因为线程的start仅仅是告诉CPU线程可以调度了，但是会不会立马调度是不确定的
                // 如果这里不睡眠 就有有这种情况出现
                // 主线程执行t1.start--Cpu没有调度t1--继续执行主线程t2-start cpu调度t2--然后再调度t1
                // 虽然我们的启动顺序是正序的（t1--t2），但是调度顺序是错乱的  t2---t1
                TimeUnit.MILLISECONDS.sleep(1);
            }
            System.out.println("所有线程启动完毕...");
        }
    }


}
