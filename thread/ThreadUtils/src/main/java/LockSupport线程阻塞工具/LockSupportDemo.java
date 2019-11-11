package LockSupport线程阻塞工具;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport是一个使用的线程阻塞工具，可以在线程内任意位置上让线程阻塞。
 * 与Thread.suspend()方法相比，它弥补了由于resume方法导致线程无法继续执行的情况
 * 与Object.wait()方法相比，它必须要先获得某个对象锁，也不会抛出interruptException
 */
public class LockSupportDemo {

    public static Object u = new Object();
    private static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    private static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());

                // park()这个静态方法可以阻塞当前线程
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();

        /**
         * unpark接触阻塞。
         * 即使unpark()发生在了part()之前的话，那么part()就不会阻塞，会立马返回。
         *
         * LockSupport类使用类似信号量机制。它为没一个线程准备了一个许可、不能累加。
         * 只要在一个地方将信号量变成了可用，那么线程永远就不会阻塞。
         */
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);

        t1.join();
        t2.join();
    }

}
