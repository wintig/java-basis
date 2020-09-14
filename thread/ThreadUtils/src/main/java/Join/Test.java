package Join;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();
        b.join(2000);
        System.out.println("主线程结束");
    }
}

class ThreadB extends Thread{
    @Override
    public synchronized void run() {
        try {
            System.out.println("B线程开始运行");
            Thread.sleep(5000);
            System.out.println("B线程结束运行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
