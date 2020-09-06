import java.util.ArrayList;
import java.util.List;

public class TwoThreadTransData {

    public static class MyList{
        private List list = new ArrayList();
        public void add(){
            list.add("侯润达");
        }
        public int size(){
            return list.size();
        }
    }

    public static class MyThreadA extends Thread{
        private MyList myList;
        public MyThreadA(MyList myList){
            this.myList = myList;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    myList.add();
                    System.out.println("添加了"+(i+1)+"个元素");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyThreadB extends Thread{
        private MyList myList;
        public MyThreadB(MyList myList){
            this.myList = myList;
        }

        @Override
        public void run() {
            try {
                while(true){
                    //System.out.println(myList.size());
                    if(myList.size()==5){
                        System.out.println("==5,线程b要退出了！");
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("退出了！");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyList list = new MyList();
        MyThreadA threadA = new MyThreadA(list);
        threadA.start();
        MyThreadB threadB = new MyThreadB(list);
        threadB.start();
    }
}