package ReentrantLock;

import java.util.concurrent.locks.LockSupport;

public class TestPark {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> testSync());

        t1.setName("t1");

        t1.start();

        Thread.sleep(1000);

        System.out.println("main");

        LockSupport.unpark(t1);

    }

    private static void testSync() {


        System.out.println(Thread.currentThread().getName());

        // 线程立马睡眠
        LockSupport.park();

        //
        System.out.println("end -");

    }

}
