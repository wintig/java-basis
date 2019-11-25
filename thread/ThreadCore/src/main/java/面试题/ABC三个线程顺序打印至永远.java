package 面试题;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程A只打印A，线程B只打印B，线程C只打印C
 * 结果ABCABCABCABC循环打印
 */
public class ABC三个线程顺序打印至永远 {

    static String[] token = {"A", "B", "C"};

    static class WorkerThread  {

        private static int status = 1;
        private int code;


        public WorkerThread(Integer code) {
            this.code = code;
        }

        public void run(ReentrantLock lock, Condition currentCon, Condition nextCon) {

            while (true) {
                lock.lock();
                try {
                    while (status % 3 != code) {
                        currentCon.await();
                    }
                    System.out.println(token[(code+2) % 3]);
                    status++;
                    nextCon.signal();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }

        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition ac = lock.newCondition();
        Condition bc = lock.newCondition();
        Condition cc = lock.newCondition();

        Thread t1 = new Thread(() -> new WorkerThread(1).run(lock, ac, bc));
        Thread t2 = new Thread(() -> new WorkerThread(2).run(lock, bc, cc));
        Thread t3 = new Thread(() -> new WorkerThread(0).run(lock, cc, ac));
        t1.start();
        t2.start();
        t3.start();
    }

}
