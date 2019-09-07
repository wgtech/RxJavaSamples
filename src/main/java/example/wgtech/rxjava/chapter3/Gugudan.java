package example.wgtech.rxjava.chapter3;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.Scanner;

public class Gugudan {
    private void plainJava() {
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input : ");
        int dan = Integer.parseInt(in.nextLine());

        for (int row = 0; row <= 9; row++) {
            System.out.println(dan + "*" + row + " = " + dan * row);
        }
    }

    /**
     *  plainJava() 와 같은 결과.<br>
     *  - 그러나, dan 변수에 대한 예외 처리를 Observable 안에서 할 수 없는 문제가 발생.<br>
     *  - dan에 음숫값을 넣으면 음수 구구단이 출력된다!!<br>
     */
    private void reactiveV1() {
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input : ");
        int dan = Integer.parseInt(in.nextLine());

        Observable<Integer> source = Observable.range(1, 9); // for 문을 Observable로 대체
        source.subscribe(row -> System.out.println(dan + " * " + row + " = " + dan * row));
    }


    /**
     *  reactiveV1을 보완한 함수<br>
     *  - Observable 시작을 변수 dan에서 해보도록 수정함.<br>
     *  - 제너릭 함수형 인터페이스인 Function< T, R >에 대한 설명은 다음과 같다.<br>
     *      1. T : 사용자에게 입력받은 Integer (Input Type)<br>
     *      2. R : 사용자에게 반환해야하는 Observable< String > (Output Type)<br>
     *          -> 여기서는 flatMap() 함수를 활용해야한다.
     */
    private void reactiveV2() {
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input : ");
        int dan = Integer.parseInt(in.nextLine());

        // 아래와 같이 미리 함수를 정의하면 subscribe() 로 호출하여 출력하면 된다.
        // gugudan 함수는 다른 곳에서도 재활용할 수 있다. 또한 디버깅 도구들을 추가해 예외처리하기 수월하다.
        Function<Integer, Observable<String>> gugudan
                = num -> Observable.range(1, 9).map(row -> num + " * " + row + " = " + dan * row);
        // 위에서 gugudan은 변수가 아니다! 함수다!

        Observable<String> source = Observable.just(dan).flatMap(gugudan); // Observable을 결과로 발행하는 flatMap() 활용하기!
        source.subscribe(System.out::println);
    }


    /**
     *  flatMap() 를 좀 더 활용하기
     */
    private void reactiveV3() {
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input : ");
        int dan = Integer.parseInt(in.nextLine());

        //  아래 코드는, reactiveV2 에서의 gugudan 함수를 inline 형태로 flapMap() 함수 내부에 삽입된 코드
        //  익숙해지는 것에 시간이 다소 걸리겠지만, 전체 로직을 한 눈에 파악할 수 있다는 장점이 있다.
        Observable<String> source = Observable.just(dan)
                                    .flatMap(num -> Observable.range(1, 9)
                                                .map(row -> num + " * " + row + " = " + dan * row));

        /**
         *  flatMap() 의 원형<br><br>
         *
         *  @SchedulerSupport(SchedulerSupport.NONE) <br>
         *  public final < R > Observable< R > flatMap(<br>
         *      Function < ? super T, ? extends ObservableSource< ? extends R >> mapper)<br>
         *
         *  <br>
         *  ObservableSource란?<br>
         *  - Observable, AsyncSubject, BehaviorSubject, ConnectableObservable 등이 공통으로 구현한 인터페이스<br>
         *  - Observable처럼 데이터를 발행할 수 있는 객체를 포괄해서 지칭한다.<br>
         *  - Single 클래스에는 SingleSource 라는 별도 인터페이스가 존재한다.
         */

    }


    private void usingResultSelector() {
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input : ");
        int dan = Integer.parseInt(in.nextLine());

        //  굉장히 깔끔하게 구구단을 만들었지만, 음수가 입력되는 경우에 대한 예외처리는 없는 코드
        Observable<String> source = Observable.just(dan)
                                    .flatMap(gugu -> Observable.range(1, 9),
                                            (gugu, i) -> gugu + " * " + i + " = " + gugu*i);

        source.subscribe(System.out::println);
        in.close();
    }


    public static void main(String[] args) {
        Gugudan gugu = new Gugudan();
        //  gugu.plainJava();
        //  gugu.reactiveV1();
        //  gugu.reactiveV2();
        //  gugu.reactiveV3();
        gugu.usingResultSelector();
    }
}
