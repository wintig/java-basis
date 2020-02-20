package 类加载器;

/**
 * 突破双亲委派
 */
public class OrderClassLoader extends ClassLoader {

    private String fileName;

    public OrderClassLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        Class re = findClass(name);
        if (re == null) {
            System.out.println("I can't load the class");
            return super.loadClass(name, resolve);
        }

        return re;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {

        Class clazz = this.findLoadedClass(className);
        if (null == clazz) {

        }

        return super.findClass(name);
    }
}
