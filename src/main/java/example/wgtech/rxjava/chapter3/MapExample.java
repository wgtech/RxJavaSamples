package example.wgtech.rxjava.chapter3;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 *  map()
 *
 *  입력값을 어떤 함수에 넣어서 원하는 값으로 변환하는 함수.
 *  String -> String, String -> Integer 또는 다른 객체로 변환 가능하다.
 *
 *  입력 데이터와 그 것을 변환해줄 함수를 이어주는 중개업자의 역할을 한다고 생각하면 이해하기 쉽다.
 *  (파이썬 언어의 map() 과 유사하다)
 *
 *  내가 원하는 값을, Function 인터페이스 객체나 람다 표현식에 넣는 것이며,
 *  원하는 함수를 정의할 수 있느냐가 관견이다.
 */
public class MapExample {

    private void marbleDiagram() {
        String[] balls = {"1", "2", "3", "5"};
        Observable<String> source = Observable.fromArray(balls)
                                            .map(ball -> ball + "⚾");
        source.subscribe(System.out::println);
    }

    private String getDiamond(String ball) {
        return ball + "⚽";
    }

    private void mapFunction() {
        Function<String, String> getDiamond = ball -> ball + "🎾";

        String[] balls = {"1", "2", "3", "5"};
        Observable<String> source = Observable.fromArray(balls).map(getDiamond);
        source.subscribe(System.out::println);
    }


    private void mappingType() {
        Function<String, Integer> ballToIndex = ball -> {
            switch (ball) {
                case "RED":     return 1;
                case "YELLOW":  return 2;
                case "GREEN":   return 3;
                case "BLUE":    return 5;
                default:        return -1;
            }
        };

        String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"};
        Observable<Integer> source = Observable
                                        .fromArray(balls)
                                        .map(ballToIndex); // 명시적인 타입 변환 없이 바로 사용 가능.
        source.subscribe(System.out::println);
    }


    public static void main(String[] args) {
        MapExample map = new MapExample();
        map.getDiamond("Soccer ");
        map.marbleDiagram();
        map.mapFunction();
        map.mappingType();
    }
}
