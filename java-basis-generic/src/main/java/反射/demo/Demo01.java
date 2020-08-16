package 反射.demo;

import 反射.bean.UserController;
import 反射.bean.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Demo01 {

    public static void main(String[] args) throws Exception {

        UserController userController = new UserController();
        UserService userService = new UserService();

        // 完成注入功能
        Class<? extends UserController> clazz = userController.getClass();

        // 获取属性
        Field userServiceField = clazz.getDeclaredField("userService");

        // 设置访问属性
        userServiceField.setAccessible(true);

        // 获取对应的set方法
        String name = userServiceField.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());   // UserService

        // 构造方法名 setUserService
        String methodName = "set" + name;

        Method method = clazz.getMethod(methodName, UserService.class);

        /**
         * 调用setUserService方法
         * 第一个参数是实例化的对象，第二个是参数
         */
        method.invoke(userController, userService);
        System.out.println(userController.getUserService());

    }

}
