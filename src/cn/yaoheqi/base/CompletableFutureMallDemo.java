package cn.yaoheqi.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * CompletableFutureMallDemo
 *
 * @author :huang yun
 * @version :2023/3/9 23:51
 */
public class CompletableFutureMallDemo {
    static List<NetMall> list = Arrays.asList(new NetMall("jd"),
            new NetMall("tb"),
            new NetMall("tm"),
            new NetMall("mt"),
            new NetMall("dy"),
            new NetMall("dd"));

    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream().map(netMall -> String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName))).collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream().map(netMall ->
                CompletableFuture.supplyAsync(() ->
                        String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName))))
                .collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (String sql : getPriceByCompletableFuture(list, "MySql")) {
            System.out.println(sql);
        }
        System.out.println("耗时" + (System.currentTimeMillis() - start) + "毫秒");
    }

    private static void future1() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                    return "hello";
                }
        );
        System.out.println(completableFuture.join());
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class NetMall {
    private String netMallName;

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
class Student {
    private String id;
    private String studentName;
    private String major;
}
