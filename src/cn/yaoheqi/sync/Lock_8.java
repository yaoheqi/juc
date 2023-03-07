package cn.yaoheqi.sync;

/**
 * Lock_8
 *
 * @author :huang yun
 * @version :2023/2/27 20:37
 * @description: 8锁
 * 1、标准访问，先打印短信还是邮件
 * ----sendSms
 * ----sendEmail
 * 2、停4秒在短信方法内，先打印短信还是邮件
 * ----sendSms
 * ----sendEmail
 * 3、新增普通的hello方法，是先打印短信还是hello
 * ----getHello
 * ----sendSms
 * 4、现在有两部手机，先打印短信还是邮件
 * ----sendEmail
 * ----sendSms
 * 5、两个静态同步方法，1部手机，先打印短信还是邮件
 * ----sendSms
 * ----sendEmail
 * 6、两个静态同步方法，2部手机，先打印短信还是邮件
 * ----sendSms
 * ----sendEmail
 * 7、1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 * ----sendEmail
 * ----sendSms
 * 8、1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 * ----sendEmail
 * ----sendSms
 */
public class Lock_8 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(100);
        new Thread(() -> {
            try {
                phone2.sendEmail();
                //phone.getHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
