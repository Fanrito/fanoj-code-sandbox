/**
 * 无限睡眠阻塞程序
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        long ONE_HOUR = 60 * 60 * 3600;
        Thread.sleep(ONE_HOUR);
        System.out.println("睡完了");
    }
}
