package example.wgtech.rxjava.chapter4;

import java.util.Random;

/**
 * 시간 측정용 예시 유틸 클래스
 */
public class CommonUtils {
    public static long startTime;

    public static void exampleStart() {
        startTime = System.currentTimeMillis();
    }

    public static void exampleComplete() {
        startTime = System.currentTimeMillis();
    }

    public static void sleep (int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getThreadName() {
        String threadName = Thread.currentThread().getName();
        if (threadName.length() > 30) {
            threadName = threadName.substring(0, 30) + "...";
        }
        return threadName;
    }

    public static void doSomething() {
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
