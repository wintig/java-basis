import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 原子更新基本类型
 * @Author wintig
 * @Create 2019-02-20 上午12:31
 */
public class AtomicIntegerTest {

    private static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args) {

        // 采用原子操作进行更新，返回值是更新之前
        System.out.println(ai.getAndIncrement());

        System.out.println(ai.get());
    }

}
