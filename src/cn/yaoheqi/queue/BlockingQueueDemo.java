package cn.yaoheqi.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueueDemo
 *
 * @author :huang yun
 * @version :2023/3/8 23:01
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.element());
        queue.remove();
        queue.remove();
        queue.offer(22,1,TimeUnit.SECONDS);
        queue.offer(11,1,TimeUnit.SECONDS);
        queue.offer(33,1,TimeUnit.SECONDS);
        queue.poll(1, TimeUnit.SECONDS);
        queue.poll(1, TimeUnit.SECONDS);
        queue.poll(1, TimeUnit.SECONDS);
        queue.poll(1, TimeUnit.SECONDS);
        System.out.println(queue.peek());
        //queue.take();
        //queue.take();
        //queue.take();
        //queue.put(6);
        //queue.put(7);
        //queue.put(8);
        //queue.put(9);
    }
}
