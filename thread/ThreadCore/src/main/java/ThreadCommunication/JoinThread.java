package ThreadCommunication;

/**
 * 创建5个线程，A线程执行完唤醒B线程
 */
public class JoinThread {

    public static void main(String[] args) {

        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(new JoinTest(Thread.currentThread()));
            threads[i].setName(i+"");
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

    }

    public static class JoinTest implements Runnable {

        Thread thread;

        public JoinTest(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {

            System.out.println("当前是线程 " + Thread.currentThread().getName());

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
