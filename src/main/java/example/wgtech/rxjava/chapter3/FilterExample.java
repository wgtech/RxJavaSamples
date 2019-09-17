package example.wgtech.rxjava.chapter3;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;

/**
 *  filter()
 *  - Observable 에서 원하는 데이터만 걸러내는 역할을 한다.
 *  - 필요없는 데이터는 제거하고 오직 관심 있는 데이터만 filter() 를 통과한다.
 *  - 필터 연산자들은 대부분 함수 이름만 봐도 기능을 유추할 수 있는 직관적인 함수이다.
 */
public class FilterExample {

    private void marbleDiagram() {
        String[] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"};
        Observable<String> source = Observable.fromArray(objs)
                                            .filter(obj -> obj.endsWith("CIRCLE")); // CIRCLE로 끝나는 것만 (동그란 원만) 통과시키는 코드
        source.subscribe(System.out::println);
    }


    private void predicateMarbleDiagram() {
        String[] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"};
        Predicate<String> filterCircle = obj -> obj.endsWith("CIRCLE"); // CIRCLE로 끝나는 것만 (동그란 원만) 통과시키는 코드
        Observable<String> source = Observable.fromArray(objs)
                                                .filter(filterCircle); // Predicate 타입 함수로 정의할 수 있다.
    }


    private void evenNumbers() {
        Integer[] data = {100, 34, 27, 99, 50};
        Observable<Integer> source = Observable.fromArray(data)
                                                .filter(number -> number % 2 == 0);
        source.subscribe(System.out::println);
    }

    private void otherFilters() {
        /**
         *  filter()와 비슷한 다른 함수들
         */

        Integer[] numbers = {100, 200, 300, 400, 500};
        Single<Integer> single;
        Observable<Integer> source;

        // 1. first(default) 함수 : Observable 첫 번째 값을 필터함. 만약 값 없이 완료되면 대신 기본값을 리턴함
        //  발행하는 데이터 중에서 첫 항목만 리턴하고 나머지는 모두 필터링된다.
        single = Observable.fromArray(numbers).first(-1);
        single.subscribe(data -> System.out.println("first() value = "+ data));

        // 2. last(default) 함수 : Observable 마지막 값을 필터함. 만약 값 없이 완료되면 대신 기본값을 리턴함.
        //  마지막 항목(onComplete 이벤트 직전 발생한 마지막 onNext 이벤트)만 리턴하고 나머지를 모두 걸러낸다.
        //  이 때, 아무 값 없이 Observable이 완료되면 인자로 넣은 기본값을 대신 발행한다.
        single = Observable.fromArray(numbers).last(999);
        single.subscribe(data -> System.out.println("last() values = " + data));

        // 3. take(N) 함수 : 최초 N개 값만 가져온다.
        //  전체 데이터 중 원하는 개수만큼 가져올 수 있기 때문에 다양한 경우에 사용할 수 있다.
        source = Observable.fromArray(numbers).take(3);
        source.subscribe(data -> System.out.println("take(3) values = " + data));

        // 4. takeLast(N) 함수 : 마지막 N개 값만 필터함.
        //  마지막 항목을 기준으로 N개의 데이터를 가져올 수 있다.
        source = Observable.fromArray(numbers).takeLast(3);
        source.subscribe(data -> System.out.println("take(3) values = " + data));

        // 5. skip(N) 함수 : 최초 N개 값을 건너뜀.
        //  처음 N개 항목을 건너뛰고 그 다음 데이터부터 활용한다.
        source = Observable.fromArray(numbers).skip(2);
        source.subscribe(data -> System.out.println("skip(2) values = " + data));

        // 6. skipLast(N) 함수 : 마지막 N개 값을 건너뜀.
        //  마지막 항목을 기준으로 N개 항목을 제외한 다음 데이터를 활용한다.
        source = Observable.fromArray(numbers).skipLast(2);
        source.subscribe(data -> System.out.println("skipLast(2) values = " + data));

        // 7. 기타 : debounce() 등이 있다.
    }


    public static void main(String[] args) {
        FilterExample filter = new FilterExample();
        filter.marbleDiagram();
        filter.predicateMarbleDiagram();
        filter.evenNumbers();
        filter.otherFilters();
    }
}
