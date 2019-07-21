package ThreadLocal;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 使用ThreadLocal {

    private static final ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>();

    public static class ParseDate implements Runnable {

        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {

                if (tl.get() == null) {
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }

                Date t = tl.get().parse("2019-07-16 20:40:" + i % 60);
                System.out.println(Thread.currentThread().getName() + " : " + t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            es.execute(new ParseDate(i));
        }

        int threadLocalHashCode = 123123130;
        int len = 32;

        binaryToDecimal(threadLocalHashCode);
        System.out.println();
        binaryToDecimal(len - 1);
        System.out.println();
        System.out.println(threadLocalHashCode & (len - 1));
    }

    public static void binaryToDecimal(int n){
        String str = "";
        while(n!=0){
            str = n%2+str;
            n = n/2;
        }
        System.out.println(str);
    }


}
