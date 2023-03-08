package cn.yaoheqi.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureDemo
 *
 * @author :huang yun
 * @version :2023/3/9 0:19
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t CompletableFuture.runAsync");
        }).get();

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t CompletableFuture.supplyAsync");
            int i = 3 / 0;
            return "supplyAsync";
        }).whenComplete((result, error) -> {
            System.out.println(result);
            System.out.println(error);
        }).get();
    }
}
