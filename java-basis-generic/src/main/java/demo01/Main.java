package demo01;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @Description
 * @Author wintig
 * @Create 2019-08-14 上午12:01
 */
public class Main {

    public static void main(String[] args) {

        Tom tom = new Tom();
        Class tomClass = tom.getClass();
        // 获得父类
        Type genericSuperclass = tomClass.getGenericSuperclass();

        ParameterizedType t = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = t.getActualTypeArguments();
        System.out.println(Arrays.toString(actualTypeArguments));


        Jerry jerry = new Jerry();
        Class<? extends Jerry> jerryClass = jerry.getClass();

/*        Type genericInterfaces = jerryClass.getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) genericInterfaces;
        System.out.println(type);*/

        Type[] genericInterfaces = jerryClass.getGenericInterfaces();
        Type genericInterface = genericInterfaces[0];
        ParameterizedType type = (ParameterizedType) genericInterface;
        System.out.println(Arrays.toString(t.getActualTypeArguments()));
    }

}
