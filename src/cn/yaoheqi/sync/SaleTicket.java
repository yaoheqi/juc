package cn.yaoheqi.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huangyun
 * @create 2023-02-24-23:50
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.lockSale();
            }
        }, "张三").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.lockSale();
            }
        }, "李四").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.lockSale();
            }
        }, "王五").start();
    }
}

/**
 * 非公平锁：线程饿死、效率高
 * 公平锁：阳光普照、效率相对低
 */
class Ticket {
    ReentrantLock lock = new ReentrantLock(true);
    private int number = 30;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第：" + number-- + "张票,还剩下：" + number + "张票");
        }
    }

    public void lockSale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：" + number-- + "张票,还剩下：" + number + "张票");
            }
        } finally {
            lock.unlock();
        }

    }
}
