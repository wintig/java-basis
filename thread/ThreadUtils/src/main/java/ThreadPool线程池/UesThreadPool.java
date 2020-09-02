package ThreadPool线程池;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UesThreadPool {

    private static class MyThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread("Wintig_" + count.getAndIncrement());
            t.setDaemon(true);
            System.out.println("create " + t);
            return t;
        }
    }

    /*没有返回值*/
    static class Worker implements Runnable {

        private String taskName;
        private Random r = new Random();

        public Worker(String taskName){
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()
                    +" process the task : " + taskName);
        }
    }

    /*有返回值*/
    static class CallWorker implements Callable<String>{

        private String taskName;
        private Random r = new Random();

        public CallWorker(String taskName){
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName()
                    +" process the task : " + taskName);
            return Thread.currentThread().getName()+":"+r.nextInt(100)*5;
        }

    }

    public static void main(String[] args) {

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("当前机器可用CPU数量 ：" + availableProcessors);


        ExecutorService threadPool = new ThreadPoolExecutor(2, 4,
                2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                //new MyThreadFactory(),  // 设置守护线程
                new ThreadPoolExecutor.DiscardPolicy()) {

            // 线程执行之前
            @Override
            protected void beforeExecute(Thread t, Runnable r) {

            }

            // 线程之后
            @Override
            protected void afterExecute(Runnable r, Throwable t) {

            }
        };

        // 无返回值
        for (int i = 0; i < 6; i++) {
            Worker worker = new Worker("worker " + i);
            System.out.println("A new task has been added : " + worker.getName());
            threadPool.execute(worker);
        }

        // 有返回值
        for (int i = 0; i <= 6; i++)
        {
 /*           CallWorker callWorker = new CallWorker("worker " + i);
            System.out.println("A new task has been added : " + callWorker.getName());
            Future<String> result = threadPool.submit(callWorker);*/

        }
    }

}
