package 类加载时机测试.初始化阶段;

public class Parent {

    static int aInt = 3;
    static {
        aInt = 0;
    }

    static class Sub extends Parent {
        static int aInt = 2;
    }

    public static void main(String[] args) {
        System.out.println(Sub.aInt);
    }

}

