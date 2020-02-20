package 引用类型;

import java.lang.ref.SoftReference;

public class 软引用_空间不足就回收 {

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

    /**
     * 预期结果
     * User{id=1, name='tig'}
     * After GC:
     * User{id=1, name='tig'}
     * null
     *
     * -Xmx10m
     *
     * @param args
     */
    public static void main(String[] args) {
        User u = new User(1, "tig");
        SoftReference<User> userSoftReference = new SoftReference<>(u);

        // 去除强引用
        u = null;

        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println("After GC:");
        System.out.println(userSoftReference.get());

        byte[] b = new byte[1024*925*7];
        System.gc();
        System.out.println(userSoftReference.get());

    }

}
