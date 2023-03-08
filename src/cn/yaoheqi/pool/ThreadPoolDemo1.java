package cn.yaoheqi.pool;

import java.util.concurrent.*;

/**
 * ThreadPoolDemo1
 *
 * @author :huang yun
 * @version :2023/3/8 23:29
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {


        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 100; i++) {
                threadPool1.execute(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool1.shutdown();
        }
    }
}
