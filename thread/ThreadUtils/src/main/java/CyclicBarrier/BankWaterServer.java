package CyclicBarrier;


import java.util.Map;
import java.util.concurrent.*;

/**
 * 模拟一个银行结算功能
 */
public class BankWaterServer implements Runnable {

    /**
     * 创建4个屏障，处理完成会，执行当前类的run方法
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);

    /**
     * 假设只有4个sheet，所以指启动4个线程
     */
    private Executor executor = Executors.newFixedThreadPool(4);

    /**
     * 保存每个sheet计算出的银流结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    private void count() {

        for (int i = 0; i < 4; i++) {

            executor.execute(new Runnable() {

                @Override
                public void run() {

                    // 计算当前sheet的银流数据(想象一下，代码略)
                    sheetBankWaterCount.put(Thread.currentThread().getName(), (int)(1 + Math.random()*10));

                    // 计算出来结果后，你就给我等着
                    try {
                        c.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }

            });

        }

    }

    @Override
    public void run() {

        int result = 0;

        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }

        // 将结果输出
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWaterServer bankWaterServer = new BankWaterServer();
        bankWaterServer.count();

    }

}
