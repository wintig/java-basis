package JMM;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotSafeThread implements Runnable {

    private enum MyEnum {
        T1(1, "test");
        private Integer code;
        private String msg;

        MyEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

    }

    public int i = 0;

    public NotSafeThread(int i) {
        this.i = i;
    }

    public void run() {
        if (i == 1){
            value.set(MyEnum.T1);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i + " " + value.get());
        value.remove();
    }

    public static ThreadLocal<MyEnum> value = new ThreadLocal<MyEnum>() {
    };

    public static void main(String[] args) {
        ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5000; i++) {
            newCachedThreadPool.execute(new NotSafeThread(i));
        }
    }
}
