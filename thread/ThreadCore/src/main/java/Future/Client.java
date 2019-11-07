package Future;

public class Client {

    public Data request(final String queryStr) {
        final FutureData future = new FutureData();

        new Thread(() -> {
            //realData的构造很慢，所以我们单开线程中进行
            RealData realData = new RealData(queryStr);
            future.setRealData(realData);
        }).start();
        return future;
    }

}
