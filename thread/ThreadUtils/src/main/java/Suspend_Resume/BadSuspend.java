package Suspend_Resume;

/**
 * suspend: 线程挂起
 * resume: 线程继续执行
 *
 * 这两个方法已经被废弃，因为被挂起的线程不会释放任何锁资源。其他线程想要访问被它占用的锁是，都会被牵连导致无法持续进行。
 * 直到对应线程上运行了resume()方法
 *
 * 如果占用他的锁不会被释放，可能会导致整个系统不正确，并且线程状态还是runnable，影响判断。
 *
 */
public class BadSuspend {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                Thread.currentThread().suspend();
            }
            System.out.println(getName() + " 退出同步代码块");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        t1.start();
        Thread.sleep(100);
        // t2线程这时候再等待t1恢复
        t2.start();
        // t1线程恢复执行
        t1.resume();
        /**
         * t2线程这时候可能还没有执行到suspend()方法，如果这时候就恢复，那么线程可以永远就恢复不了
         * Thread.sleep(100);
         * 在这里尝试暂停一下，等待线程执行了suspend方法，然后执行resume就能解决
         */
        t2.resume();
        t1.join();
        t2.join();
        System.out.println("main 方法结束");
    }

}
