package cn.yaoheqi.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinDemo
 *
 * @author :huang yun
 * @version :2023/3/9 0:06
 */
class MyTask extends RecursiveTask<Integer> {

    /**
     * 拆分差值不能超过10，计算10以内的运算
     */
    private static final Integer VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= VALUE) {
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            int middle = (begin + end) / 2;
            MyTask task1 = new MyTask(begin, middle);
            MyTask task2 = new MyTask(middle + 1, end);
            task1.fork();
            task2.fork();
            result += task1.join();
            result += task2.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask task1 = new MyTask(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        try {
            ForkJoinTask<Integer> submit = forkJoinPool.submit(task1);
            Integer result = submit.get();
            System.out.println(result);
        } finally {
            forkJoinPool.shutdown();
        }
    }
}
