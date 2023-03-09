package cn.yaoheqi.base;

import java.util.concurrent.TimeUnit;

/**
 * DaemonDemo
 *
 * @author :huang yun
 * @version :2023/3/9 22:18
 */
public class DaemonDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t " + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "t1");
        t1.setDaemon(true);
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t ");
    }
}
