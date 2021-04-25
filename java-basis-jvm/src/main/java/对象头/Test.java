package 对象头;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class Test {

    static A a = new A();

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }

}
