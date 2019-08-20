package example.wgtech.rxjava.chapter2;

import example.wgtech.rxjava.chapter2.models.Order;
import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleExample {
    /**
     * Single 클래스는 RxJava 초기부터 존재하는 Observable의 특수한 형태이다.
     * Observable 클래스는 무한하게 발행할 수 있던 것과는 달리,
     * Single 클래스는 오직 1개의 데이터만 발행하도록 한정되어 있다.
     *
     * 따라서, Single 클래스는
     * 결과가 유일한 서버 API를 호출할 때 유용하게 사용할 수 있다.
     */

    private void just() {
        // 정적 팩토리 함수 just()
        Single<String> source = Single.just("Hello Single");
        source.subscribe(System.out::println);
    }

    private void fromObservable() {
        // Single은 Observable의 특수한 형태이므로, Observable에서 변환 할 수 있다.
        // 다음은 Observable에서 Single 클래스를 사용하는 다양한 방법들이다.

        /**
         *  1. 기존 Observable에서 Single 객체로 변환하기
         *  - 기존 Observable에서 첫 번째 값을 발행하면 onSuccess 이벤트를 호출 후 종료한다.
         */
        Observable<String> source = Observable.just("Hello Single");
        Single.fromObservable(source).subscribe(System.out::println);


        /**
         *  2. single() 함수를 호출해 Single 객체 생성하기
         *  - Observable에서 값이 발행되지 않을 때도 인자로 넣은 기본값을 대신 발행한다.
         */
        Observable.just("Hello Single")
                .single("default Item")
                .subscribe(System.out::println);


        /**
         *  3. first() 함수를 호출하여 Single 객체 생성하기
         *  - 다수의 데이터를 발행할 수 있는 Observable을 first() 함수를 호출 할 때 Single 객체로 변환하는 것이다.
         *  - 하나 이상의 데이터를 발행하더라도 첫 데이터를 발행한 후 onSuccess 이벤트가 발생한다.
         */
        String[] colors = {"Red", "Blue", "Gold"};
        Observable.fromArray(colors)
                .first("default Value")
                .subscribe(System.out::println);

        /**
         *  4. empty Observable 에서 Single 객체 생성하기
         *  - 3번 예시처럼, 첫 데이터 발행 후 onSuccess 이벤트가 발생한다.
         *    그리고 2번 예시처럼 Observable에서 값이 발행되지 않아도, 기본값을 갖는 Single 객체로 변환할 수 있다.
         */
        Observable.empty()
                .single("default value")
                .subscribe(System.out::println);

        /**
         *  5. take() 함수에서 Single 객체 생성하기
         *  - String 같은 기본 타입 뿐만 아니라, 사용자 정의 클래스도 Single에서 사용할 수 있다.
         *    아래의 경우, 결과적으로 subscribe()를 통해 Order 객체의 toString()이 호출된다.
         */
        Observable.just(new Order("ORD-1"), new Order("ORD-2"))
                .take(1)
                .single(new Order("default Order"))
                .subscribe(System.out::println);

    }

    private void errorCase() {
        // just() 에 여러 값을 넣을 때 에러가 발생된다.
        // onNext 이벤트가 발생할 때 에러가 발생된다.
        Single<String> source = Observable.just("Hello Single", "Error")
                                        .single("default item");
        source.subscribe(System.out::println);
    }


    public static void main(String[] args) {
        SingleExample example = new SingleExample();
        example.just();
        example.fromObservable();
        //example.errorCase();
    }

}
