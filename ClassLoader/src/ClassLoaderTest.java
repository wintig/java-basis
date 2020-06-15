import java.lang.reflect.Constructor;

public class ClassLoaderTest {

    public void printClassLoader() {

        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println("classLoader类加载器为：" + classLoader);

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("当前线程类加载器为：" + contextClassLoader);


    }

    // 加载扩展Ext目录下的类
    public void loadExtClass() throws Exception {

        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();

        System.out.println("方法loadExtClass使用的类加载器为" + classLoader);

        // 加载ExtClass
        Class<?> clazz = classLoader.loadClass("ExtClass");

        Constructor<?> constructor = clazz.getConstructor();
        System.out.println("ExtClass类正在加载" + constructor.newInstance());
    }

    // 加载classpath下的类
    public void loadClassUnderClasspath(String name) throws Exception {

        System.out.println("当前类的类加载器为" + ClassLoaderTest.class.getClassLoader());

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        System.out.println("和当前线程上线文绑定的类加载器为" + contextClassLoader);

        Class<?> aClass = contextClassLoader.loadClass(name);


        Constructor<?> contextClassLoaderConstructor = aClass.getConstructor(ClassLoader.class);
        // 执行构造器
        contextClassLoaderConstructor.newInstance(contextClassLoader);
    }

}

