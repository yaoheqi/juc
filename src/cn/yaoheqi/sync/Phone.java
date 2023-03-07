package cn.yaoheqi.sync;

/**
 * Phone
 *
 * @author :huang yun
 * @version :2023/2/27 20:32
 * synchronized实现同步的基础：Java中的每一个对象都可以作为锁，具体表现为以下3种形式：
 * 1、对于普通同步方法，锁的是当前实例对象。
 * 2、对于静态同步方法，锁的是当前类的Class对象
 * 3、对于同步代码块，锁的是Synchronized括号里配置的对象
 */
public class Phone {
    public  synchronized void sendSms() throws Exception {
        Thread.sleep(1000);
        System.out.println("----sendSms");
    }

    public static synchronized void sendEmail() throws Exception {
        System.out.println("----sendEmail");
    }

    public void getHello() throws Exception {
        System.out.println("----getHello");
    }
}
