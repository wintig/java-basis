package java运行时数据区;

public class 局部变量表测试 {

    @Override
    public String toString() {
        add();
        return super.toString();
    }

    public int add() {
        int c;
        int a = 500;
        int b = 200;
        try {
            c = a + b;
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return c;
    }

}

