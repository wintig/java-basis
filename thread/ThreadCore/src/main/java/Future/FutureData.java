package Future;

public class FutureData implements Data {

    protected RealData realData = null; // FutureData是RealData的包装
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();        // RealData已经注入，通知getResult()方法
    }

    @Override
    public String getResult() {
        while (!isReady) {
            try {
                wait(); // 一直等待，直到RealDate被注入
            } catch (InterruptedException e) {

            }
        }
        return realData.result;
    }

}
