/**
 *  Hello, RxJava2 !
 *  wgtech.dev
 */

package example.wgtech.rxjava.chapter1;

import io.reactivex.Observable;
// Observable 클래스 : 데이터의 변화가 발생하는 데이터 소스 (Data Source)

public class FirstExample {
    public void emit() { // emit = 발행하다
        Observable.just("Hello", "RxJava 2!")
                    // just() : 가장 단순한 Observable 선언 방식.
                    // Integer와 같은 래퍼 타입부터
                    // Order 같은 사용자 정의 클래스의 객체도 인자로 넣을 수 있다.
                .subscribe(System.out::println);
                    // subscribe() : Observable을 구독한다.
                    // Observable은 subscribe() 함수를 호출해야
                    // 비로소 변화한 데이터를 구독자에게 발행한다. (just() 함수만 있어서는 발행하지 않는다.)
                    // Observer 패턴과 동일하기에, 반드시 데이터를 수신할 구독자가 subscribe() 함수를 호출해야한다.

                    // System.out::println 은 Java8의 Method Reference를 활용했다.
                    //  data -> System.out.println(data) 와 동일하다.
                    //  Observable이 발행하는 데이터는 data 인자로 들어온다.
    }

    public static void main(String args[]) {
        FirstExample demo = new FirstExample();
        demo.emit();
    }
}
