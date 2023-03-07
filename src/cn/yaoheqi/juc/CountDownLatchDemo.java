package cn.yaoheqi.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatchDemo 原子计数器
 * 演示   CountDownLatch
 *
 * @author :huang yun
 * @version :2023/3/7 23:23
 */
public class CountDownLatchDemo {
    //6个同学陆续离开教室之后,班长锁门
    public static void main(String[] args) throws InterruptedException {
        //6个同学陆续离开教室之后
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 班长锁门走人了");
    }
}
