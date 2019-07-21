package ThreadLocal;


public class ThreadLocal内存溢出 {


    public static void main(String[] args) {

        new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                TestClass t = new TestClass(i);
                t.printId();
                t = null;


                t.threadLocal.remove();
            }

        }).start();


    }

    static class TestClass{
       private int id;
       private int[] arr;
       private ThreadLocal<TestClass> threadLocal;

       TestClass(int id){
            this.id = id;
            arr = new int[1000000];
            threadLocal = new ThreadLocal<>();
            threadLocal.set(this);
       }

       public void printId(){
             System.out.println(threadLocal.get().id);
       }
   }

}
