package 垃圾回收.GC_Root;

public class StackLocalParameter {

    public static void testGC(){
        StackLocalParameter s = new StackLocalParameter();
        s = null;
    }

}

