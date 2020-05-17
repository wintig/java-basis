package 类加载时机测试.被动引用;

public class 被动引用02 {

    public static int value = 123;

    public static void main(String[] args) {
        System.out.println(FinalFieldClass.constString);
    }
}

class FinalFieldClass {

    public static final String constString = "CONST";

    static {

        System.out.println("FinalFieldClass init");
    }

}
