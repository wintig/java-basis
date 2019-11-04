package Deamom;

public class DeamonDemo {

    public static class DeamonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("守护线程：I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DeamonT();
        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);
        System.out.println("主线程退出");

        // 当一个java程序中只有守护线程的时候，java虚拟机才会退出
    }

}
