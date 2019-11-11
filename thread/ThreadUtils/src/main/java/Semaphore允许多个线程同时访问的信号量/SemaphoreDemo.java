package Semaphore允许多个线程同时访问的信号量;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class SemaphoreDemo implements Runnable {

    // 允许5个线程同时访问，第二个参数fair表示是否公平，会影响性能
    final Semaphore semaphore = new Semaphore(5, false);

    @Override
    public void run() {
        try {
            // 获得一个准入许可证。如果无法获得，则线程会阻塞等待，直到有线程释放一个许可证或者当前线程终端
            semaphore.acquire();

            // 尝试获得一个许可证，如果恒公返回true，失败则返回false。不会阻塞等待
            // boolean success = semaphore.tryAcquire();

            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放许可证资源
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(semaphoreDemo);
        }
    }
}
