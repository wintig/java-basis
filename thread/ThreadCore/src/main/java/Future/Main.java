package Future;


import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Client client = new Client();
        // 这里会立即返回，因为得到的是FutureData而不是RealData
        Data data = client.request("name");
        System.out.println("请求完毕");

        try {
            // 这里可以用一个sleep代替对其他业务逻辑的处理，
            // 再处理这些业务逻辑过程中，RealData被创建，从而充分利用等待时间

            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        // 使用真实的数据
        System.out.println("数据 = " + data.getResult());

    }


}
