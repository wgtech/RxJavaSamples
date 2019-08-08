/**
 *  Hello, RxJava2 !
 *  wgtech.dev
 */

package example.wgtech.rxjava.chapter2;

import io.reactivex.Observable;
// Observable 클래스 : 데이터의 변화가 발생하는 데이터 소스 (Data Source)

public class FirstExampleModified {
    public void emit() { // emit = 발행하다
        Observable.just(1, 2, 3, 4, 5, 6)
                // just()
                // 인자로 넣은 데이터를 차례로 발행하려고 Observable을 생성한다. (물론 이 때 subscribe() 를 호출해야 시작.)
                // 이 때 하나의 값을 넣을 수 있고, 인자로 최대 10개의 값을 넣을 수 있다.
                // 단, 타입은 모두 같아야한다.

                .subscribe(System.out::println);
                // subscribe()
                // 내가 동작시키기 원하는 것을 사전에 정의해둔 다음 실제 그것이 실행되는 시점을 조절할 때 사용.

                /**
                 *  인자의 갯수에 따라 다르게 움직이는 subscribe([onNext, onError, onComplete])
                 *
                 *  x : onNext, onComplete 이벤트 무시하고 onError 이벤트 발생시, OnErrorNotImplementedException 던짐.
                 *  주로, Observable로 작성한 코드를 테스트하거나 디버깅할 때 사용
                 *
                 *  1개 : onNext 이벤트를 처리. 물론 onError 이벤트 발생시, OnErrorNotImplementedException 던짐.
                 *
                 *  2개 : onNext, onError 이벤트 처리
                 *
                 *  3개 : onNext, onError, onComplete 이벤트를 모두 처리.
                 */

    }

    public static void main(String args[]) {
        FirstExampleModified demo = new FirstExampleModified();
        demo.emit();
    }
}
