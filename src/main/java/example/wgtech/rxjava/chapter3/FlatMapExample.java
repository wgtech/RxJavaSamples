package example.wgtech.rxjava.chapter3;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 *  flapMap()
 *
 *  flapMap()은 map() 을 좀 더 발전시킨 함수이다.
 *  map() 과는 달리, 똑같이 함수에 넣더라도 결과가 Observable로 나온다는 차이점이 있다.
 *
 *  즉, flatMap() 함수는 일대다 혹은 일대일 Observable 함수이다.
 *
 *  다수의 데이터를 반드시 발행해야하는 것이 아니고, 또한 1개의 데이터만을 반드시 발행해야하는 것이 아니다.
 *  결과가 Observable 객체이기 때문에 1개 또는 여러 개의 데이터를 발행할 수 있다.
 */
public class FlatMapExample {

    private void marbleDiagram() {
        // 함수를 별도로 정의하는 것이 가장 먼저 겪어야할 관문
        Function<String, Observable<String>> getDoubleDiamonds =
                ball -> Observable.just(ball + "⚽", ball + "🎾");

        String[] balls = {"1", "3", "5"};

        Observable<String> source = Observable.fromArray(balls)
                                        .flatMap(getDoubleDiamonds);
        source.subscribe(System.out::println);
    }


    public static void main(String[] args) {
        FlatMapExample flatMap = new FlatMapExample();
        flatMap.marbleDiagram();
    }
}
