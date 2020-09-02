package ThreadPool线程池;

import java.util.Random;

public class WintigThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        WintigThreadPool threadPool = new WintigThreadPool(0, 3);
        threadPool.execute(new MyTask("Task1"));
        threadPool.execute(new MyTask("Task2"));
        threadPool.execute(new MyTask("Task3"));
        threadPool.execute(new MyTask("Task4"));
        threadPool.execute(new MyTask("Task5"));


        Thread.sleep(10000);

        threadPool.destroy();

    }

    static class MyTask implements Runnable {

        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(1000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务 " + name + " 完成");
        }
    }

}
