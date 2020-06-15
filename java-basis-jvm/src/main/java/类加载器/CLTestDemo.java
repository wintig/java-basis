package 类加载器;

public class CLTestDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader);
            // 获取父类加载器
            classLoader = classLoader.getParent();
        }
        System.out.println(classLoader);

    }

}
