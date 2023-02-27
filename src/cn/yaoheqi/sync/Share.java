package cn.yaoheqi.sync;

/**
 * @author huangyun
 * @create 2023-02-26-21:40
 */
public class Share {
    private int number = 0;

    public synchronized void incr() throws InterruptedException {
        while (number != 0) {
            wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        while (number != 1) {
            wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        notifyAll();
    }
}
