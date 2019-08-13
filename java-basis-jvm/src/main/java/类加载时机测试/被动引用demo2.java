package 类加载时机测试;


public class 被动引用demo2 {

    public static void main(String[] args) {
        /**
         * 这里就很有意思了，static静态代码块里面的东西并没有打印
         *
         * 是因为在编译阶段通过了常量传播优化，将常量的值“hello world”存储到了 “被动引用demo2”类的常量池，以后对常量的引用
         * 都会转化成对类自身常量池的引用。在编译后，这两个类就没有了任何关系
         */
        System.out.println(ConstClass.HELLOWORLD);
    }

}

class ConstClass {

    static {
        System.out.println("ConstClass init!!!");
    }

    public static final String HELLOWORLD = "hello world";

}
