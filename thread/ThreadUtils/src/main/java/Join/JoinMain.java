package Join;

public class JoinMain {

    public volatile static int i = 0;

    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (int j = 0; j < 1000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();
        // 当前现在等待自增线程完毕
        addThread.join();
        System.out.println(i);
    }

}
