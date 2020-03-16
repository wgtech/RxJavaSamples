package example.wgtech.rxjava.chapter4;

import io.reactivex.Observable;

/**
 * range()
 *
 * 주어진 값 (n) 부터 m 개의 Integer 객체를 발행한다.
 * interval() 와 timer() 함수는 Long 객체를 발행하지만, range() 함수는 Integer 객체를 발행한다는 것이 다른 차이점이다.
 */
public class RangeExample {

    public static void main(String[] args) {
        RangeExample e = new RangeExample();
        e.forLoop();
    }

    private void origin() {
        /**
         *  @SchedulerSupport(SchedulerSupport.NONE)
         *  public static Observable<java.lang.Long> range (final int start, final int count)
         *
         *  interval() 함수나 timer() 함수와는 다르게 Scheduler 에서 실행되지 않는다. 따라서 현재 스레드에서 실행이 가능하다.
         *  range() 함수는 반복문 (for 문, while 문)을 대체할 수 있다.
         */
    }

    private void forLoop() {
        Observable<Integer> source = Observable.range(1, 10)            // 1부터 10까지의 숫자 생성
                                                .filter(number -> number % 2 == 0); // 그 중에 짝수만 걸러낸다.
        source.subscribe(Log::it);

        /**
         * 결과
         * main | 1584369790908 | value = 2
         * main | 1584369790908 | value = 4
         * main | 1584369790908 | value = 6
         * main | 1584369790908 | value = 8
         * main | 1584369790908 | value = 10
         *
         * 현재 Thread 에서 실행되기 때문에, CommonUtils.sleep() 메서드를 호출하지 않는다.
         */
    }
}
