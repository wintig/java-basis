package ReentrantLock;

/**
 *
 * 重入锁ReentrantLock顾名思义他表示该锁支持一个线程对资源的重复加锁
 * synchronize也是一个重入锁，但是试用synchronize来做同步处理的时候，锁的获取和释放都是隐式的，他主要实现原理是通过编译后加入不同机器指令来完成的
 * 而ReentrantLock它是基于AQS(AbstractQueuedSynchronized)来实现的。
 *
 */
public class MyReentrantLock {



}
