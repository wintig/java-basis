package 反射.bean;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)   // 属性
@Inherited
@Documented
public @interface Autowired {
}
