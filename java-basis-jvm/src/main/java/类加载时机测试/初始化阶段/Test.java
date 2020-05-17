package 类加载时机测试.初始化阶段;

public class Test {
    static {
        aInt = 0;        // 可以正常赋值
       // System.out.println(aInt);   // 如果要访问就会提示"非法向前引用"
    }

    static int aInt = 3;
}
