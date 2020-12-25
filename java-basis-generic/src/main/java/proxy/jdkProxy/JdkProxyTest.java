package proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyTest {

    public static void main(String[] args) {

        //代理的真实对象
        RealSubject realSubject = new RealSubject();

        ClassLoader loader = realSubject.getClass().getClassLoader();
        Class[] interfaces = realSubject.getClass().getInterfaces();

        Subject subject = (Subject)Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
                Object returnValue = method.invoke(realSubject, args);

                return returnValue;
            }
        });


        System.out.println("动态代理对象的类型："+subject.getClass().getName());

        subject.SayHello("jdkProxy");

        System.out.println("=======");

        realSubject.name = "wintig";
        subject.SayHello("jdkProxy");

    }


}
