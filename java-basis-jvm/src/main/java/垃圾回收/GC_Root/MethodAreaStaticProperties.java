package 垃圾回收.GC_Root;

public class MethodAreaStaticProperties {

    public static final MethodAreaStaticProperties m = new MethodAreaStaticProperties("final");
    public MethodAreaStaticProperties(String name){}

    public static void testGC(){
        MethodAreaStaticProperties s = new MethodAreaStaticProperties("staticProperties");
        s = null;
    }

}
