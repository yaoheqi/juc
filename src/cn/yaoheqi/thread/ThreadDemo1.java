package cn.yaoheqi.thread;

import cn.yaoheqi.lock.Share;
import cn.yaoheqi.lock.ShareResource;

/**
 * @author huangyun
 * @create 2023-02-26-20:32
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        Share share = new Share();
        Thread 张三 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "张三");

        Thread 李四 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "李四");

        Thread 王五 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "王五");

        Thread 赵六 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "赵六");
        张三.start();
        李四.start();
        王五.start();
        赵六.start();
    }
}

