package ThreadCommunication;

import java.util.concurrent.TimeUnit;

/**
 * 采用共享内存的方式来执行线程间的通信
 */
public class VolatileThread implements Runnable {

    private static volatile boolean flag = true;

    @Override
    public void run() {

        while (flag){
            System.out.println(Thread.currentThread().getName() + "正在运行。。。");
        }

        System.out.println(Thread.currentThread().getName() +"执行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileThread aVolatile = new VolatileThread();


        new Thread(aVolatile).start();

        System.out.println("==========================") ;

        TimeUnit.MILLISECONDS.sleep(10) ;


        // 主线程修改参数后，子线程会立马感知到关闭
        System.out.println("关闭线程");
        flag = false;

    }


}
