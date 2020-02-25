package example.wgtech.rxjava.chapter4;

import static example.wgtech.rxjava.chapter4.CommonUtils.getThreadName;

/**
 *  Log 클래스
 *
 *  CommonUtils 클래스와 함께 활용하여 로그를 출력한다.
 *  이 때, 실행되는 스레드 이름과 실행 시간을 표시할 수 있다.
 */
public class Log {
    public static void it (Object obj) { // i : information, t : time
        long time = System.currentTimeMillis() - CommonUtils.startTime;
        System.out.println(getThreadName() + " | " + time + " | " + "value = " + obj);
    }
}
