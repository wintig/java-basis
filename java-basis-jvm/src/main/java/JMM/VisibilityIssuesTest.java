package JMM;

public class VisibilityIssuesTest extends Thread {

    private static volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag = " + flag);
    }

    public static void main(String[] args) {

        double pi = 3.14;               // A
        double r = 1.0;                 // B
        double area = pi * r * r;       // C

        VisibilityIssuesTest vi = new VisibilityIssuesTest();
        vi.start();
        while (true) {
            synchronized (vi) {
                if (flag) {
                    System.out.println("Hello World");
                }
            }
        }



    }

}
