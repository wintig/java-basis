package 反射.demo;

import 反射.bean.Autowired;
import 反射.bean.UserController;

import java.util.stream.Stream;

public class 注解方式 {

    public static void main(String[] args) {
        UserController userController = new UserController();

        // 获取Class
        Class<? extends UserController> clazz = userController.getClass();

        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            // 获取属性是否有注解
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation != null) {
                field.setAccessible(true);
                // 获取当前属性的类型，有了之后可以创建具体的对象
                Class<?> type = field.getType();
                // 创建对象
                try {
                    Object o = type.newInstance();
                    field.set(userController, o);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(userController.getUserService());

    }

}
