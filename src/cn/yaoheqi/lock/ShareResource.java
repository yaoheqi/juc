package cn.yaoheqi.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huangyun
 * @create 2023-02-26-21:42
 */
public class ShareResource {
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            for (int l = 0; l < 5; l++) {
                System.out.println(Thread.currentThread().getName() + "::" + (l + 1) + "：轮数" + loop);
            }
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }

    }

    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            for (int l = 0; l < 10; l++) {
                System.out.println(Thread.currentThread().getName() + "::" + (l + 1) + "：轮数" + loop);
            }
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }

    }

    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            for (int l = 0; l < 15; l++) {
                System.out.println(Thread.currentThread().getName() + "::" + (l + 1) + "：轮数" + loop);
            }
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }

    }
}
