package CyclicBarrier;


import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier字面意思是可循环使用（Cyclic）的屏障（Barrier）
 *
 * CyclicBarrier想当与一个门，初始化的关键字就相当于你要初始化几道门。
 * 每个线程相当于钥匙，当你调用await方法后，就说明你插入了一把钥匙。
 *
 * 这道门安全系数特别高，只有当你把所有钥匙（对应线程）都插入门锁（await方法）中，所有线程才能继续执行下面方法
 *
 */
public class CyclicBarrierTest {

    // 构造器表示平常拦截的线程数
    static CyclicBarrier c = new CyclicBarrier(3);

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

}
