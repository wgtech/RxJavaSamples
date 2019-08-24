package example.wgtech.rxjava.chapter2.subjects;

import io.reactivex.subjects.BehaviorSubject;

/**
 *  BehaviorSubject
 *  - 구독자가 구독을 하면 가장 최근 값 혹은 기본값을 넘겨주는 클래스
 *  - 사용예시로, 온도 센서에서 값을 받아온다면 가장 최근 온도 값을 받아오는 동작을 구현 할 수 있다.
 *    (처음 얻을 땐 초깃값을 반환한다.)
 */
public class BehaviorSubjectExample {

    private void marbleDiagram() {
        // BehaviorSubject 클래스는 createDefault() 함수로 생성한다.
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("6"); // 발행값이 없을 때 기본값을 설정해준다.

        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.onNext("5");
        subject.onComplete();
    }


    public static void main(String[] args) {
        BehaviorSubjectExample behavior = new BehaviorSubjectExample();
        behavior.marbleDiagram();
    }
}
