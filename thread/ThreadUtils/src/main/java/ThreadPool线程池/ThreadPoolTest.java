package ThreadPool线程池;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Description
 * @Author wintig
 * @Create 2018-12-25 下午11:25
 */
public class ThreadPoolTest {

    /**
     * 线程池的执行流程：
     *
     * 执行第一个线程任务，如果没有执行上面prestartAllCoreThreads（预启动所有核心线程）方法，那么当前线程池中的线程是没有的，这时候会创建一个线程来执行任务；
     * 后面任务来了，即使当前线程池中有线程空闲，如果没有达到最小线程数（corePoolSize），那么会直接创建一个新的线程执行任务；
     *
     * 当队列中没有空闲线程，并且线程数大于等于最小线程数（corePoolSize）的时候，这时候就丢到阻塞队列中去，阻塞队列有上面几种，看情况选择；
     *
     * 如果核心线程数的所有队列都满了，这时候就创建新的线程来执行任务，如果小于最大线程数（maximumPoolSize），那么就创建一个新的线程来处理任务。
     * 如果达到最大线程数，则调用RejectedExecutionHandler的方法执行饱和策略
     */
    public static void main(String[] args) {

        /**
         * corePoolSize ：最小线程数
         *
         * maximumPoolSize ：最大线程数，线程池允许创建的最大线程数。
         *
         * workQueue ：任务队列，用户保存等待执行的任务阻塞队列，可以选择以下几个阻塞队列（如果设置了无界队列，那么最大线程数将没有任何意义v ）
         * ArrayBlockingQueue：基于数据的有界阻塞队列，FIFO
         * LinkedBlockingQueue：基于链表的阻塞队列，无界队列
         * SynchronizedQueue：一个不存储元素的阻塞队列，每个插入操作，必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞
         * PriorityBlockingQueue：无界阻塞的优先队列
         *
         * threadFactory：用户创建线程的工厂
         *
         * RejectedExecutionHandler（饱和策略）：当队列和线程池都满了，需要一种策略处理新提交的线程
         * AbortPolicy：直接抛异常
         * CallerRunsPolicy：只用调用者所在的线程来运行任务
         * DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务
         * DiscardPolicy：不处理，丢弃
         * 也可以根据应用场景实现RejectedExecutionHandler接口自定义策略。比如记录日志，或者持久化存储
         *
         * keepAliveTime（线程活动保持时间）：线程池工作空闲后，保持存活的时间
         *
         * TimeUnit时间单位
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                50,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10)
        );


        // 调用后，线程池会提前创建并启动所有的基本线程
        // executor.prestartAllCoreThreads();


        /**
         * 线程池执行任务的两种策略
         */

        // 不需要返回，所以不能判断是否执行成功
        executor.execute(new Domino());

        // 用于提交需要返回值的任务。线程池会返回一个future类型的对象
        Future<?> future = executor.submit(new CallTest());

        try {

            Object s = future.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            // 处理中断异常

        } catch (ExecutionException e) {
            // 处理无法执行任务异常

        }


        /**
         * 线程的关闭，有两种方式shutdown，shutdownNow。他们都是通过遍历线程池中的工作线程，然后逐个调用线程的interrupt方法来中断线程。
         *
         * shutdown：只是将线程状态设置成shutdown状态，然后终端所有没有正在执行的任务线程
         *
         * shutdownNow：首先将线程的状态改为STOP，然后尝试停止所有正在执行或暂停的任务，并返回等待执行任务列表。
         *
         */

        executor.shutdown();

        List<Runnable> runnables = executor.shutdownNow();


        // 只要调用了两个关闭方法中的任意一个，isShutdown方法就会返回true
        System.out.println("is shutdown : " + executor.isShutdown());

        // 当所有任务都关闭后，isTerminated才返回true
        System.out.println("is terminate : " + executor.isTerminated());

    }



}

class Domino implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : run success");
    }
}

class CallTest implements Callable {

    @Override
    public Object call() throws Exception {
        return Thread.currentThread().getName() + " : call success";
    }
}


