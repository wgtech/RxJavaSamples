package example.wgtech.rxjava.chapter2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;

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

    private void subscribeLambda() {
        Observable<Integer> source = Observable.create(
                (ObservableEmitter<Integer> emitter) -> {
                    emitter.onNext(100);
                    emitter.onNext(200);
                    emitter.onNext(300);
                    emitter.onComplete();
                }
        );

        source.subscribe(data -> System.out.println("Result : " + data));
        /**
         * 21번째 줄 System.out::println 은 data -> System.out.println(data) 를 줄인 것이다.
         *
         * System.out::println 같은 형태를 Java 8에서는 Method Reference 라고 한다.
         * Reactive Programming에서는 앞서 설명한 Lambda 표현식과 Method Reference를 적극적으로 사용하는 것이 좋다.
         *
         * Lambda Expression, Method Reference 사용시 우선순위를 고려해서 사용여부를 판단하자.
         *  1. Method Reference 로 축약 할 수 있는지 확인.
         *  2. Lambda Expression 을 활용 할 수 있는지 확인.
         *  3. 1번 또는 2번을 활용할 수 없다면 Anonymous 객체나 Member 변수로 표현.
         */
    }

    private void subscribeAnonymously() {
        /**
         * Anonymous 객체 활용 예시
         *
         * 아래의 코드는 subscribe()의 원형을 알아야하고,
         * Consumer<T> 클래스의 메서드와 Consumer의 Type도 매번 입력을 해줘야하므로 번거롭다.
         */

        Observable<Integer> source = Observable.create(
                (ObservableEmitter<Integer> emitter) -> {
                    emitter.onNext(100);
                    emitter.onNext(200);
                    emitter.onNext(300);
                    emitter.onComplete();
                }
        );

        source.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Result : " + integer);

            }
        });

        /**
         *  source.subscribe(integer -> {
         *     System.out.println("Result : " + integer);
         *  });
         *
         *  와 동일하다.
         */





    }

    public static void main(String[] args) {
        ObservableCreateExample example = new ObservableCreateExample();
        example.basic();

        example.notSubscribed();
    }
}
