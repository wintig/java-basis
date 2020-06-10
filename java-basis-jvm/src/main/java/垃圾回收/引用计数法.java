package 垃圾回收;

public class 引用计数法 {

    public static void testGC(){
        // 定义2个对象
        ReferenceCountingGC a = new ReferenceCountingGC("objA");
        ReferenceCountingGC b = new ReferenceCountingGC("objB");
        // 相互引用
        a.instance = b;
        b.instance = a;
        // 置空个字的声明引用
        a = null;
        b = null;
    }
}

class ReferenceCountingGC{
    public Object instance;
    public ReferenceCountingGC(String name){}
}