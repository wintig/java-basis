package 限流;


import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterDemo令牌桶算法 {

    // 每秒只能处理2个请求
    private static RateLimiter limiter = RateLimiter.create(2);

    public static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            // 阻塞试限流
            // limiter.acquire();

            /**
             * 在有些场景中，如果系统无法处理请求，为了保证服务质量，更倾向于直接丢弃，从而避免可能的崩溃
             */
            if (!limiter.tryAcquire()) {
                System.out.println("丢弃了一个请求");
                continue;
            }

            new Thread(new Task()).start();
        }
    }

}
