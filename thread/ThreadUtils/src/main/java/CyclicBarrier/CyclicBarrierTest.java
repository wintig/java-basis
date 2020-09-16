package CyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    /**
     *
     * barrier 英文是屏障，障碍，栅栏的意思。cyclic是循环的意思，就是说，这个屏障可以循环使用。
     *
     * 一组线程会互相等待，直到所有线程都到达一个同步点。
     * 这个就非常有意思了，就像一群人被困到了一个栅栏前面，只有等最后一个人到达之后，他们才可以合力把栅栏（屏障）突破。
     *
     * 应用场景：
     * 一组运动员比赛 1000 米，只有在所有人都准备完成之后，才可以一起开跑（额，先忽略裁判吹口哨的细节）。
     *
     * 定义一个 Runner 类代表运动员，其内部维护一个共有的 CyclicBarrier，每个人都有一个准备时间，准备完成之后，会调用 await 方法，等待其他运动员。
     * 当所有人准备都 OK 时，就可以开跑了。
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 第一个构造的参数，指的是需要几个线程一起到达，才可以使所有线程取消等待。
         * 第二个构造，额外指定了一个参数，用于在所有线程达到屏障时，优先执行 barrierAction。
         */
        // CyclicBarrier barrier = new CyclicBarrier(3);  //①

        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            try {
                System.out.println("等裁判吹口哨...");
                //这里停顿两秒更便于观察线程执行的先后顺序
                Thread.sleep(2000);
                System.out.println("裁判吹口哨->>>>>");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Runner runner1 = new Runner(barrier, "张三");
        Runner runner2 = new Runner(barrier, "李四");
        Runner runner3 = new Runner(barrier, "王五");

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(runner1);
        service.execute(runner2);
        service.execute(runner3);

        service.shutdown();

    }

}


class Runner implements Runnable{

    private CyclicBarrier barrier;
    private String name;

    public Runner(CyclicBarrier barrier, String name) {
        this.barrier = barrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            //模拟准备耗时
            Thread.sleep(new Random().nextInt(5000));
            System.out.println(name + ":准备OK");
            barrier.await();
            System.out.println(name +": 开跑");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e){
            e.printStackTrace();
        }
    }
}
