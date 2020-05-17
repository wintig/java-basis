package 类加载时机测试.被动引用;

public class 被动引用01 {
    public static void main(String[] args) {
        System.out.println(Child.v);
    }
}
class Parent {
    static {
        System.out.println("Parent init");
    }
    public static int v = 100;
}
class Child extends Parent {
    static {
        System.out.println("Child init");
    }
}
