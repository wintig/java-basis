package ReentrantLock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 他是一个可重入锁，当先线程可以进入多次和synchronize相比更加灵活
 */
public class ReentrantLock1 extends Thread{

    private MyService service;

    public ReentrantLock1(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }

    public static void main(String[] args) {

        MyService service = new MyService();

        ReentrantLock1 reentrantLock1 = new ReentrantLock1(service);
        ReentrantLock1 reentrantLock2 = new ReentrantLock1(service);
        ReentrantLock1 reentrantLock3 = new ReentrantLock1(service);
        ReentrantLock1 reentrantLock4 = new ReentrantLock1(service);

        reentrantLock1.start();
        reentrantLock2.start();
        reentrantLock3.start();
        reentrantLock4.start();
    }

}

class MyService {

    private Lock lock = new ReentrantLock();

    public void testMethod() {

        lock.lock();

        for (int i = 0; i < 5; i++) {
            System.out.println("threadName = " + Thread.currentThread().getName() + " i = " + i);
        }

        lock.unlock();
    }

}
