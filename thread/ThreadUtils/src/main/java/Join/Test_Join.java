package Join;


public class Test_Join extends Thread{


    public static void main(String[] args) {

        Test_Join testJoin = new Test_Join();
        testJoin.start();

        /**
         * 调用了join方法，当前线程被阻塞了，直到join线程执行完毕后当前线程再执行。
         */
        Test_Join.testJoin(testJoin);

        System.out.println("主线程结束...");
    }

    public static void testJoin(Thread thread) {
        try {

            /**
             *
             * join原理其实就是调用了wait方法
             *
             * 当前线程调用了wait方法那么就会进行等待状态，释放调用了join方法的对象锁。
             * join线程的执行run逻辑，直到join的逻辑执行完毕后，由“操作系统”notify等待调用join方法的线程
             *
             */

            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        synchronized (this) {

            System.out.println("子线程开始执行");

            for (int i = 0; i < 5; i++) {
                System.out.println("执行子线程逻辑等待" + ( i + 1 )+ "s ...");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            System.out.println("子线程执行结束");
        }

    }
}
