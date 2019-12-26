package ThreadPool线程池.实现;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CustomThreadPool {

    private final ReentrantLock lock = new ReentrantLock();


    // 核心线程数
    private volatile int corePoolSize;

    // 最大线程数
    private volatile int maximumPoolSize;

    // 线程活动保持时间
    private volatile long keepAliveTime;
    private TimeUnit unit;

    // 工作队列线程
    private final BlockingQueue<Runnable> workQueue;

    // 拒绝策略
    private volatile RejectedExecutionHandler handler;

    public CustomThreadPool(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {

        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;

    }

    /**
     * 执行线程逻辑
     * @param command the task to execute
     */
    public void execute(Runnable command) {

        if (command == null) {
            throw new NullPointerException();
        }

        // 当前线程数小于corePoolSize那么就扩容
        if (workQueue.size() < corePoolSize) {
            addWorker(command, true);
            return;
        }

        // 如果写入失败的话
        if (!workQueue.offer(command)) {

            // 小于最大线程数则创建线程
            if (workQueue.size() < maximumPoolSize) {
                addWorker(command, false);
                return;
            } else {
                // 超过最大线程
                reject(command);
            }

        }

    }

    /**
     *
     * @param firstTask 该worker启动之后要执行的第一个任务
     * @param core true ：表示创建的是核心线程和corePoolSize绑定，
     *             false：表示非核心线程maximumPoolSize绑定。
     * @return
     */
    private boolean addWorker(Runnable firstTask, boolean core) {



        return false;
    }

    final void reject(Runnable command) {

    }

}
