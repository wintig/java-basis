package Wait_Notify_Notifyall;


public class Test_Notify extends Thread {

    public static void main(String[] args) throws InterruptedException {

        Test_Notify testNotify = new Test_Notify();
        testNotify.start();

        testNotify.sleep(5000);

        synchronized (testNotify) {
            testNotify.notify();
        }

        System.out.println("主线程执行结束");

    }

    @Override
    public synchronized void run() {

        System.out.println("子线程执行中");

        try {
            /**
             * wait方法释放当前线程持有的锁对象，以及CPU执行权限。所以执行wait方法必须要持有锁。
             *
             * 调用wait方法会使得当前线程进入等待状态，直到被其他线程调用此object上的notify方法或者notifyAll方法唤醒
             *
             * notify随机唤醒“等待”当前线程对象锁的一个线程
             * notifyAll唤醒“所有等待”当前线程对象锁的一个线程
             */
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("子线程执行结束");

    }



}
