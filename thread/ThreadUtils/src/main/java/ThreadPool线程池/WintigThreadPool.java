package ThreadPool线程池;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WintigThreadPool {

    // 缺省线程初始值
    private static int word_count = 5;

    // 存放任务
    private final BlockingQueue<Runnable> taskQueue;

    // 工作线程
    private WorkThread[] workThreads;
    private final int work_number;  // 实际的工作数量

    public WintigThreadPool() {
        this(100, word_count);
    }

    public WintigThreadPool(int taskCount, int workNumber) {

        if (workNumber < 0) {
            workNumber = word_count;
        }

        if (taskCount <= 0) {
            taskCount = 100;
        }

        this.taskQueue = new ArrayBlockingQueue<>(taskCount);
        this.work_number = workNumber;

        // 初始化工作线程
        workThreads = new WorkThread[work_number];
        for (int i = 0; i < work_number; i++) {
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
    }

    public void destroy() {
        System.out.println("准备销毁线程池....");
        for (int i = 0; i < work_number; i++) {
            workThreads[i].stopWorker();
            workThreads[i] = null;  // help gc
        }
        taskQueue.clear();
    }

    // 放入任务，如果满了就会阻塞
    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // 内部类，工作线程的实现
    private class WorkThread extends Thread {
        @Override
        public void run() {
            Runnable take = null;
            try {
                // 如果当前线程不中断
                while (!isInterrupted()) {
                    take = taskQueue.take();
                    if (take != null) {
                        System.out.println(getId() + "正在执行");
                        take.run();
                    }
                    take = null;
                }
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }

        // 停止工作
        public void stopWorker() {
            interrupt();
        }

    }

}
