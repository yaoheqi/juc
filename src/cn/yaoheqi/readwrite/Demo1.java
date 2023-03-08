package cn.yaoheqi.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Demo1
 *
 * @author :huang yun
 * @version :2023/3/8 22:38
 */
public class Demo1 {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        //锁降级

        readLock.lock();
        System.out.println(Thread.currentThread().getName() + "\t read");

        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + "\t atguigu");


        writeLock.lock();
        readLock.unlock();
    }
}
