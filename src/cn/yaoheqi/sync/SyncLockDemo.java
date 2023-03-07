package cn.yaoheqi.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SyncLockDemo
 *
 * @author :huang yun
 * @version :2023/2/28 23:41
 * 可重入锁
 */
public class SyncLockDemo {
    public static void main(String[] args) {
        //Lock演示可重入锁
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t外层");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t中层");
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "\t内层");
                    } finally {
                        //lock.unlock();
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t内层");
            } finally {
                lock.unlock();
            }
        }, "t2").start();

        //new SyncLockDemo().add();
        //Object o = new Object();
        //new Thread(() -> {
        //    synchronized (o) {
        //        System.out.println(Thread.currentThread().getName() + "\t外层");
        //        synchronized (o) {
        //            System.out.println(Thread.currentThread().getName() + "\t中层");
        //            synchronized (o) {
        //                System.out.println(Thread.currentThread().getName() + "\t内层");
        //            }
        //        }
        //    }
        //}, "aa").start();
    }

    public synchronized void add() {
        add();
    }
}
