package cn.yaoheqi.base;

import java.util.concurrent.*;

/**
 * CompletableFutureUseDemo
 *
 * @author :huang yun
 * @version :2023/3/9 23:33
 */
public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "\t " + Thread.currentThread().isDaemon());
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("-----1秒后出结果+\t" + result);
                if (result > 5) {
                    throw new ArithmeticException();
                }
                return result;
            }, executorService).whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("-----计算完成，更新系统：" + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况：" + e.getCause() + "\t " + e.getMessage());
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        System.out.println(Thread.currentThread().getName() + "\t 线程去忙其他任务了");
    }

    private static void future1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----1秒后出结果+\t" + result);
            return result;
        });
        System.out.println(completableFuture.get());
        System.out.println(Thread.currentThread().getName() + "\t 线程去忙其他任务了");
    }
}
