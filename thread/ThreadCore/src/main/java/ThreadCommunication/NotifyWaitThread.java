package ThreadCommunication;

/**
 * 等待通知机制，交替打印线程
 */
public class NotifyWaitThread {

    private int start = 1;

    private boolean flag = true;


    public static void main(String[] args) {

        NotifyWaitThread notifyWaitThread = new NotifyWaitThread();

        Thread t1 = new Thread(new OuNum(notifyWaitThread));
        t1.setName("偶数线程");

        Thread t2 = new Thread(new JiNum(notifyWaitThread));
        t2.setName("奇数线程");

        t1.start();
        t2.start();

    }

    /**
     * 偶数线程
     */
    public static class OuNum implements Runnable {

        private NotifyWaitThread notifyWaitThread;

        public OuNum(NotifyWaitThread notifyWaitThread) {
            this.notifyWaitThread = notifyWaitThread;
        }

        @Override
        public void run() {

            while (notifyWaitThread.start <= 1000) {

                synchronized (NotifyWaitThread.class) {

                    if (!notifyWaitThread.flag) {
                        System.out.println(Thread.currentThread().getName() + "当前数字为：" + notifyWaitThread.start);
                        notifyWaitThread.start++;
                        notifyWaitThread.flag = false;
                        NotifyWaitThread.class.notify();
                    } else {

                        // 如果当前线程不是打印奇数线程就进入等待，随后释放锁
                        try {
                            NotifyWaitThread.class.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

        }
    }

    /**
     * 奇数线程
     */
    public static class JiNum implements Runnable {

        private NotifyWaitThread notifyWaitThread;

        public JiNum(NotifyWaitThread notifyWaitThread) {
            this.notifyWaitThread = notifyWaitThread;
        }

        @Override
        public void run() {


            while (notifyWaitThread.start <= 1000) {

                synchronized (NotifyWaitThread.class) {

                    if (notifyWaitThread.flag) {
                        System.out.println(Thread.currentThread().getName() + "当前数字为：" + notifyWaitThread.start);
                        notifyWaitThread.start++;
                        notifyWaitThread.flag = true;

                        // 通知线程并且释放锁
                        NotifyWaitThread.class.notify();
                    } else {

                        // 如果当前线程不是打印奇数线程就进入等待，随后释放锁
                        try {
                            NotifyWaitThread.class.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }


            }

        }
    }

}
