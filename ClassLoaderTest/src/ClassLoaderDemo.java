import java.lang.reflect.Constructor;

public class ClassLoaderDemo {

    public static void main(String[] args) throws Exception {

        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        Class<?> classLoaderTest = classLoader.loadClass("ClassLoaderTest");


        // 利用反射得到类
        Constructor<?> constructor = classLoaderTest.getConstructor();
        ClassLoaderTest obj = (ClassLoaderTest)constructor.newInstance();


        //obj.loadExtClass();

        obj.loadClassUnderClasspath("MyDriver");
    }


}
