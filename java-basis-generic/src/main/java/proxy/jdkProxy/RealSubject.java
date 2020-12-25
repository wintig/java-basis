package proxy.jdkProxy;


public class RealSubject implements Subject {

    public String name = "tian.shi";

    public void SayHello(String name) {
        System.out.println(this.name + ": hello " + name);
    }

}