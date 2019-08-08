package example.wgtech.rxjava.chapter2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class ObservableCreateExample {
    private void basic() {
        Observable<Integer> source = Observable.create(
            (ObservableEmitter<Integer> emitter) -> {
                emitter.onNext(100);
                emitter.onNext(200);
                emitter.onNext(300);
                emitter.onComplete();
            }
        );

        // subscribe() with customizing results
        source.subscribe(data->System.out.println("Result : " + data));

        // subscribe() Integer results only
        source.subscribe(System.out::println);
    }

    private void notSubscribed() {
        /**
         * Observable.create() 만 호출하고 subscribe() 함수를 호출하지 않은 메서드
         * => subscribe() 를 호출하지 않음으로써 실제로 값이 출력되지 않는다
         * => subscribe() 사용 시점의 중요성!
         */
        Observable<Integer> source = Observable.create(
                (ObservableEmitter<Integer> emitter) -> {
                    emitter.onNext(100);
                    emitter.onNext(200);
                    emitter.onNext(300);
                    emitter.onComplete();
                }
        );
    }

    public static void main(String[] args) {
        ObservableCreateExample example = new ObservableCreateExample();
        example.basic();

        example.notSubscribed();
    }
}
