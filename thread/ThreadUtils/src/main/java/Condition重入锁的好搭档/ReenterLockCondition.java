package Condition重入锁的好搭档;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    // 生成一个与当前重入锁绑定的condition对象
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();

            /**
             * 会使当前线程等待，同时释放当前锁。
             * 当其他线程中使用signal()方法或者signalAll()方法时，线程会重新获得锁并继续执行。
             * 或者当前线程被中断时，也能跳出等待。和Object.wait()方法相似。
             */
            condition.await();

            // 和await方法基本相同，但是await可以相应中断，这个不会。
            //condition.awaitUninterruptibly();

            System.out.println("线程继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition reenterLockCondition = new ReenterLockCondition();
        Thread t1 = new Thread(reenterLockCondition);
        t1.start();

        Thread.sleep(2000);

        // 通知t1继续执行
        lock.lock();

        // 唤醒一个在等待中的线程，signalAll()方法会唤醒所有再等待中的线程
        condition.signal();

        // signal()方法调用之后，系统会从当前Condition对象的等待队列中唤醒一个线程，让他继续执行
        // 所以这里需要释放锁，如果不释放锁，那么其他线程也不可能会唤醒了
        lock.unlock();
    }
}
