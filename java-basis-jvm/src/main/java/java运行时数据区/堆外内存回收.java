package java运行时数据区;

import java.nio.ByteBuffer;

public class 堆外内存回收 {

    public static void main(String[] args) {
        for(int i=0;i<102400;i++){
            ByteBuffer.allocateDirect(1024*1024);
            System.out.println(i);
            System.gc();
        }

    }

}
