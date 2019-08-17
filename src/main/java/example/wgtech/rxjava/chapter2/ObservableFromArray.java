package example.wgtech.rxjava.chapter2;

import io.reactivex.Observable;

import java.util.stream.IntStream;

/**
 *  fromXXX()
 *  단일 데이터가 아닌 데이터를 다루는 함수
 */
public class ObservableFromArray {

    private void integerArray() {
        Integer[] arr = {100, 200, 300}; // 숫자 뿐만 아니라 사용자 정의 클래스 객체도 넣을 수 있다.
        Observable<Integer> source
                = Observable.fromArray(arr); // fromXXX() : Array 데이터를 다루는 함수
        source.subscribe(System.out::println);
    }

    private void intArrayWrong() {
        // 명시적 래퍼 타입(Integer)가 아닌 기본 자료형 사용할 경우
        int[] intArray = new int[]{400, 500, 600};
        Observable.fromArray(intArray).subscribe(System.out::println);
    }

    private void intArrayRight(int[] intArray) {
        Observable<Integer> source = Observable.fromArray(
                IntStream.of(intArray).boxed().toArray(Integer[]::new)
        );
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        ObservableFromArray ofa = new ObservableFromArray();
        ofa.integerArray();
        ofa.intArrayWrong(); // [I@3f49dace 등의 해쉬코드가 출력된다.
                             // RxJava에서는 int 배열을 인식시키려면 반드시 래퍼 타입을 사용해야한다.
        ofa.intArrayRight(new int[]{400, 500, 600});
    }

}
