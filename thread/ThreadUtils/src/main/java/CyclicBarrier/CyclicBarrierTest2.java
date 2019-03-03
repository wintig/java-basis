package CyclicBarrier;


import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierTest2 {

    // 第二个参数就是，要开门了，先执行一下A这个方法
    static CyclicBarrier c = new CyclicBarrier(2, new A());

    public static void main(String[] args) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {

                }

                System.out.println(1);
            }
        }).start();


        try {
            c.await();
        } catch (Exception e) {

        }
        System.out.println(2);

    }

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }

    }

}
