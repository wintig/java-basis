package Future;


/**
 * RealData是最终需要使用的数据模型。它的构造很慢。
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String result) {
        System.out.println("开始构造真实数据");
        // resultData的构造可能很慢，需要用户等很久，这里用sleep模拟
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(result);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
        this.result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
