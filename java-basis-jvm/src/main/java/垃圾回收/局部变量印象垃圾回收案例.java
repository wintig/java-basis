package 垃圾回收;

/**
 * @Description
 * @Author wintig
 * @Create 2019-09-04 下午10:40
 */
public class 局部变量印象垃圾回收案例 {

    /**
     * 运行时需要加入参数 -verbose:gc
     * @param args
     */
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.out.println(a);
        System.gc();
    }

    /**
     * 执行垃圾回收的是时候，byte还是被变量引用所以无法被垃圾回收
     */
    public void localvarGc1() {
        byte[] a = new byte[64 * 1024 * 1024];
        System.gc();
    }

    /**
     * 垃圾回收之前，a被设置为了null，所以只去了强引用。
     * 所以垃圾回收可以顺利的回收byte空间
     */
    public void localvarGc2() {
        byte[] a = new byte[64 * 1024 * 1024];
        a = null;
        System.gc();
    }

    /**
     * 进行垃圾回收之前，先使得局部变量a失效，虽然a离开了作用域。但是a还是存在于局部变量表中
     * 并且a也指向这块内存。所以byte无法被回收
     */
    public void localvarGc3() {
        {
            byte[] a = new byte[64 * 1024 * 1024];
        }
        System.gc();
    }

    /**
     * 进行垃圾回收之前，局部变量a失效，但是变量a离开了作用局，这时候b会服用a的槽位。
     * 使得b覆盖了变量a。
     * 这时候byte内存没有没有被引用所以会被垃圾回收
     */
    public void localvarGc4() {
        {
            byte[] a  = new byte[64 * 1024 * 1024];
        }
        int b = 10;
        System.gc();
    }
}
