package 代理;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest implements MethodInterceptor {

    public static void main(String[] args) {

        // 创建Enhancer对象，用于生成代理类
        Enhancer enhancer = new Enhancer();
        // 设置哪个类需要代理
        enhancer.setSuperclass( String.class );
        // 设置怎么代理，这里传入的是Callback对象-MethodInterceptor父类
        CglibTest logInterceptor = new CglibTest();
        enhancer.setCallback( logInterceptor );
        // 获取代理类实例
        String stringController = ( String )enhancer.create();

        // 测试代理类
        System.out.println(stringController.toString());

    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        if (method.equals("toString")) {
            System.out.println("这是代理方法");
        }

        return o;
    }
}
