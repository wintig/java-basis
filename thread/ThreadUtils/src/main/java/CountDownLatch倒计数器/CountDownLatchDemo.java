package CountDownLatch倒计数器;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * countDownLatch翻译叫门闩，很奇怪。
 * 这个类似一个倒计数器，一种典型的场景就是火箭发射。在火箭发射前，为了保证万无一失往往还需要对各项设备、
 * 仪器进行检查。只有等所有的设备检查完毕后，引擎才能点火。
 */
public class CountDownLatchDemo implements Runnable{

    // 5就是当前计数器的计数个数
    private static CountDownLatch end = new CountDownLatch(5);

    private static CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("检查完毕");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exec.submit(demo);
        }

        // await()要求主线程等待所有线程执行完毕等待检查
        end.await();

        // 发射火箭
        System.out.println("发射火箭...");
        exec.shutdown();
    }
}
