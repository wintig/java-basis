package ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    static  ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
           testSync();
        });


    }


    private static void testSync() {

        lock.lock();

        String name = Thread.currentThread().getName();
        System.out.println(name);

        lock.unlock();

    }

}
