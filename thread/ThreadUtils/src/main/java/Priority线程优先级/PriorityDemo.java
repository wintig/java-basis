package Priority线程优先级;

public class PriorityDemo {

    public static class HightPrioity extends Thread {
        private static int count = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count ++;
                    if (count > 10000000) {
                        System.out.println("HightPrioity is complete");
                    }
                }
            }
        }
    }

    public static class LowPrioity extends Thread {
        private static int count = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count ++;
                    if (count > 10000000) {
                        System.out.println("LowPrioity is complete");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        HightPrioity high = new HightPrioity();
        LowPrioity lowPrioity = new LowPrioity();

        high.setPriority(Thread.MAX_PRIORITY);
        lowPrioity.setPriority(Thread.MIN_PRIORITY);
        lowPrioity.start();
        high.start();
    }


}
