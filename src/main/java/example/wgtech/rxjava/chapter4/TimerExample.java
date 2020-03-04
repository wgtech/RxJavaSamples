package example.wgtech.rxjava.chapter4;

import io.reactivex.Observable;
import io.reactivex.annotations.SchedulerSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * timer()
 *
 * interval() 함수와 유사하지만 '단 한 번만' 실행하는 함수이다.
 * 일정 시간이 지난 후에 한 개의 데이터를 발행하고 onComplete() 이벤트가 발생한다.
 */

public class TimerExample {

    public static void main(String[] args) {
        TimerExample t = new TimerExample();
        t.showTime();
    }

    private void origin() {
        /**
         *  @SchedulerSupport(SchedulerSupport.COMPUTATION)
         *  public static Observable<java.lang.Long> timer (long delay, java.util.concurrent.TimeUnit unit)
         *
         *  현재 스레드가 아닌 계산 스케줄러에서 실행되는 것도 동일하고, 발행되는 데이터도 inteval() 함수의 첫 번째 값인 OL 이다.
         */
    }

    private void showTime() { // timer() 예제
        CommonUtils.exampleStart();
        Observable<String> source = Observable.timer(500L, TimeUnit.MILLISECONDS)
                                            // notUsed : 현재 시각을 표시하는 람다 표현식 인자 이름 (Long)
                                            .map(notUsed -> new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }


}
