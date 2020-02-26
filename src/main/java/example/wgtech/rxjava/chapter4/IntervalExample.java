package example.wgtech.rxjava.chapter4;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * interval()
 *
 * 일정 시간 간격으로 데이터 흐름을 생성한다.
 * 주어진 시간 간격으로 0부터 1씩 증가하는 Long 객체를 발행한다. (이 때 Long은 Wrapper Class인 Long 객체이다.)
 * 주로 사용하는 함수는 다음과 같다.
 */
public class IntervalExample {

    public static void main(String[] args) {
        IntervalExample e = new IntervalExample();
        e.printNumbers();
    }

    public void origin() { // 함수 원형

        /**
         @SchedulerSupport(SchedulerSupport.COMPUTATION)
         public static Observable<Long> interval(long period, TimeUnit unit)

             일정 시간(period)를 쉬었다가 데이터를 발행한다.
             @SchedulerSupport(SchedulerSupport.COMPUTATION) 라는 Annotation은 interval() 함수의 동작이 '계산 스케쥴러에서 실행된다'라는 의미다.
             계산을 위한 별도의 스레드에서 동작한다고 생각하는 것이 이해하기 쉬울 것이다.
         */

        /**
         public static Observable<Long> interval(long initialDelay, long period, TimeUnit unit)

             위 첫 번째 interval 함수 원형에서의 동작과 같으며, 최초 지연 시간 (initialDelay)을 조절할 수 있다.
             보통 초기 지연 시간 없이 바로 데이터를 발행하기 위해서 사용한다. (initialDelay를 0으로 입력한다)
         */
    }


    public void printNumbers() {
        CommonUtils.exampleStart();
        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                                            .map(data -> (data + 1) * 100)
                                            .take(5);
        source.subscribe(Log::it);
        CommonUtils.sleep(100);
    }


}
