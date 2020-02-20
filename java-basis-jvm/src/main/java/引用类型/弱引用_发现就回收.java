package 引用类型;

import java.lang.ref.WeakReference;

public class 弱引用_发现就回收 {

    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        User u = new User(1, "tig");
        WeakReference<User> userWeakReference = new WeakReference<>(u);

        u = null;

        System.out.println(userWeakReference.get());
        System.gc();
        // 不管当前内存空间是否足够，都会回收内存
        System.out.println("After GC:");
        System.out.println(userWeakReference.get());
    }

}
