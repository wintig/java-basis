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

}
