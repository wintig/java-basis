package demo01;

/**
 * @Description
 * @Author wintig
 * @Create 2019-08-14 上午12:00
 */
public class Jerry implements Mouse<String> {

    public void eat(String s) {
        System.out.println("杰瑞吃" + s);
    }
}
