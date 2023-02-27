package cn.yaoheqi.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author huangyun
 * @create 2023-02-26-22:26
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
  /*     1、演示list： List<String> list = new ArrayList<>();
        List<String> list = new Vector<>();
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        2、演示set：Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        3、演示map
        */
//        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
