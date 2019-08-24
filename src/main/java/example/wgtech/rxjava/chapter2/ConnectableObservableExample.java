package example.wgtech.rxjava.chapter2;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class ConnectableObservableExample {

    private void marbleDiagram() throws InterruptedException {
        String[] dt = {"1", "3", "5"};
        Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS) // interval() 함수는 테스트 코드를 작성할 때 많이 활용, 100ms 간격으로 데이터 발행
                // method chaining 기법
                .map(Long::intValue)
                .map(integer -> dt[integer])
                .take(dt.length);
        ConnectableObservable<String> source = balls.publish();
        source.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        source.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        source.connect();

        Thread.sleep(250);
        source.subscribe(data -> System.out.println("Subscriber #3 => " + data));
        Thread.sleep(100); // 이 Thread.sleep() 함수를 끝내면 모든 Subscriber들이 순서에 맞게 각각의 발행값을 수신 받는다. 그리고 모든 구독자들이 구독 해지된다.
    }


    public static void main(String[] args) {
        ConnectableObservableExample connectable = new ConnectableObservableExample();
        try {
            connectable.marbleDiagram();
        } catch (InterruptedException e) {
            System.err.println("Interrupt has been occurred when thread is sleeping shortly");
            e.printStackTrace();
        }
    }
}
