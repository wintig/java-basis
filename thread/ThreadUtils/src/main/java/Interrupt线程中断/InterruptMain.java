package Interrupt线程中断;

public class InterruptMain {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断了");
                    break;
                }
                Thread.yield();
            }
        });

        thread.start();
        Thread.sleep(5000);
        /**
         * 中断线程，其实本质是改变线程内部的一个中断标志位
         * 我们虽然对线程执行了中断处理，但是这个线程还是没有结束，因为没有进行任何操作
         */
        thread.interrupt();
    }

}
