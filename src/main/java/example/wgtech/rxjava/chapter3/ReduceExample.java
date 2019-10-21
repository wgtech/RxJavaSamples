package example.wgtech.rxjava.chapter3;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * reduce()
 * - 발행한 데이터를 모두 사용하여 어떤 최종 결과 데이터를 합성할 때 활용되는 함수.
 * - 함수형 프로그래밍의 가장 기본 연산자인 map, filter, reduce 패턴을 이루는 마지막 필수 함수.
 *   (Observable에 입력된 데이터를 필요한 map() 함수로 매핑)
 *   (원하는 데이터만 추출할 때는 불필요한 데이터를 걸러내주는 filter() 함수를 호출)
 *   (상황에 따라 발행된 데이터를 취합하여 어떤 결과를 만들어낼 때는 reduce 계열의 함수 사용)
 * - Observable을 이용해 들어오는 데이터를 1개씩 모아 최종 결과를 만들어야할 때 주로 사용.
 *   (주로 수치와 관련된 계산 문제에서 자주 활용된다)
 */
public class ReduceExample {

    private void marbleDiagram() { // 1, 3, 5 라는 원을 받아 먼저 받은 원을 내부로 흡수하여, 1, 3, 5 모두 포함하는 더 큰 원을 출력한다.
        String[] balls = {"1", "3", "5"};
        Maybe<String> source = Observable.fromArray(balls)
                                    .reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        ReduceExample r = new ReduceExample();
        r.marbleDiagram();
    }
}
