package 类加载时机测试;


public class 被动引用demo1 {

    public static void main(String[] args) {

        /**
         * 这里会先输出 “SuperClass init!”，而不会输出“SubClass init!”
         * 对于静态字段，只有直接定义这个字段的类才会被初始化
         */
        //System.out.println(SubClass.value);

        /**
         * 这里没有任何数据打印出来，因为并没有类被初始化
         */
        SuperClass[] src = new SuperClass[10];
    }

}

class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}