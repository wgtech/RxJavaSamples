package example.wgtech.rxjava.chapter2;

import io.reactivex.Observable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/**
 * Java9 부터 사용가능한 Publisher 는 Java9의 표준인 Flow API의 일부이다.
 */

public class ObservableFromPublisher {

    private void basic() {
        Publisher<String> publisher = (Subscriber<? super String> s) -> {
            s.onNext("Hello Observable.fromPublisher()");
            s.onComplete();
        };
        Observable<String> source = Observable.fromPublisher(publisher);
        source.subscribe(System.out::println);
    }


    private void withoutLambda() {
        Publisher<String> publisher = new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onNext("Hello Observable.fromPublisher() without lambda");
                s.onComplete();
            }
        };
        Observable<String> source = Observable.fromPublisher(publisher);
        source.subscribe(System.out::println);
    }


    public static void main(String[] args) {
        ObservableFromPublisher publisher = new ObservableFromPublisher();
        publisher.basic();
        publisher.withoutLambda();
    }
}
