package demo01;

/**
 * @Description
 * @Author wintig
 * @Create 2019-08-13 下午11:59
 */
public class Tom extends Cat<String, Integer> {

    @Override
    public void eat(String o) {

        System.out.println("Tom, 不吃" + o);
    }
}
