import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Description 采用原子更新数组
 * @Author wintig
 * @Create 2019-02-20 上午12:34
 */
public class AtomicIntegerArrayTest {

    private static int[] value = new int[] {1, 2};

    private static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }

}
