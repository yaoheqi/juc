package cn.yaoheqi.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * SemaphoreDemo    6辆汽车，3个停车位
 *
 * @author :huang yun
 * @version :2023/3/8 0:04
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建Semaphore，设置许可数量
        Semaphore semaphore = new Semaphore(3);
        //模拟6辆汽车
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到了车位");
                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5) % 5);
                    System.out.println(Thread.currentThread().getName() + " ------离开了车位");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
        //TimeUnit.SECONDS.sleep(3);
        //for (int i = 1; i < 7; i++) {
        //    new Thread(() -> {
        //        try {
        //            semaphore.release();
        //            System.out.println(Thread.currentThread().getName() + " 添加许可证");
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        //    }, String.valueOf(i)).start();
        //}
    }
}
