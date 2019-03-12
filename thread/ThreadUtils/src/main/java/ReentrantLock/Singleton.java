package ReentrantLock;

/**
 * @Description
 * @Author wintig
 * @Create 2019-03-11 下午12:54
 */
public class Singleton {

    private Singleton() {

    }

    private static class Inner {
        private static Singleton s = new Singleton();
    }

    public static Singleton getSingleton() {
        return Inner.s;
    }

}
