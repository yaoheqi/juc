package cn.yaoheqi.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierDemo    集齐7课龙珠就可以召唤神龙
 *
 * @author :huang yun
 * @version :2023/3/7 23:56
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("*****集齐7课龙珠就可以召唤神龙");
        });
        for (int i = 1; i <= 14; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 星龙珠被收集到了");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, (i % 7 + 1) + "").start();
        }
    }
}
