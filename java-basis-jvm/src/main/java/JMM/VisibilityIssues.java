package JMM;

public class VisibilityIssues extends Thread {

    private static boolean flag = false;

    @Override
    public void run() {
        while (!flag) {
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        VisibilityIssues vi = new VisibilityIssues();
        // 启动vi线程
        vi.start();
        Thread.sleep(200);

        // 修改状态位，main线程等待vi线程执行完成
        flag = true;
        vi.join();
        System.out.println("done");
    }

}
