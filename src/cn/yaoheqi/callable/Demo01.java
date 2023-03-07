package cn.yaoheqi.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demo01   比较两个接口
 *
 * @author :huang yun
 * @version :2023/3/7 22:40
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runnable接口创建线程
        new Thread(new MyThread1(), "AA").start();
        //Callable接口,直接创建线程，报错
        //new Thread(new MyThread2(), "BB").start();
        FutureTask<Integer> task1 = new FutureTask<Integer>(new MyThread2());
        FutureTask<Integer> task2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in callable");
            return 1024;
        });
        //lambda表达式
        new Thread(task1, "mary").start();
        new Thread(task2, "lucy").start();
        AtomicInteger atomicInteger = new AtomicInteger();
        while (!task2.isDone()) {
            //System.out.println("wait...");
            atomicInteger.incrementAndGet();
        }
        System.out.println(task1.get());
        System.out.println(task2.get());
        System.out.println(Thread.currentThread().getName() + " come over");
        System.out.println(atomicInteger.get());
        //FutureTask原理：未来任务，可取消的异步运算，实现了RunnableFuture接口，RunnableFuture中含有run方法，继承了Runnable、Future接口
        //1、老师上课，口渴了，去买水不适合，讲课进程需继续，开一个新线程、找班上班长帮我买水，把水买回来，需要的时候直接get
        //2、4个同学分别进行运算，1同学 1+2+...+5，2同学 10+11+..+50，3同学 60+61+62，4同学 100+200，第二个同学计算量比较大，FutureTask单独开一个线程给2同学计算，先汇总1、3、4，最后等2同学计算完成，统一汇总
        //3、考试，做会做的题目，最后看不会做的题目，汇总一次
    }
}

/**
 * 实现Runnable接口
 */
class MyThread1 implements Runnable {
    @Override
    public void run() {
        System.out.println("实现Runnable接口");
    }
}

/**
 * 实现Callable接口
 */
class MyThread2 implements Callable {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " come in callable");
        return 200;
    }
}
