package 引用类型;

import java.lang.ref.WeakReference;

public class 弱引用_发现就回收 {

    public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    // 将User作为弱引用，回收的时候回收的user而不是school
    public static class School extends WeakReference<User> {
        public School(User user) {
            super(user);
        }
    }

    public static void main(String[] args) {

        // 建立了一个弱引用school和user的关系
        School school = new School(new User("wintig"));

        System.out.println("before gc ： " + school.get());
        System.out.println("=== GC ===");
        System.gc();
        // 不管内存够不够，都直接回收
        System.out.println("after gc ： " + school.get());
    }

}
