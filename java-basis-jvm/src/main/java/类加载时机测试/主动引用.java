package 类加载时机测试;

public class 主动引用 {

    public static void main(String[] args) {
        Child child = new Child();
    }

}

class Parent {
    static {
        System.out.println("Parent init");
    }
}

class Child extends Parent {
    static {
        System.out.println("Child init");
    }
}
