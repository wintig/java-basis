package Join;

/**
 * 模拟join
 */
public class My_Join_Achieve extends Thread{

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        My_Join_Achieve myJoinAchieve = new My_Join_Achieve();

        myJoinAchieve.start();

        // 主线程上锁了，等待子线程执行完毕然后唤醒
        synchronized (lock) {
            lock.wait();
        }

        System.out.println("主线程执行完毕");

    }

    @Override
    public void run() {

        synchronized (lock) {

            for (int i = 0; i < 5; i++) {

                System.out.println("执行子线程逻辑等待" + ( i + 1 )+ "s ...");

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            // 唤醒等待的线程，自己实现的话，就需要我们自己唤醒。如果join的话是交由操作系统唤醒
            lock.notify();
        }

    }
}
