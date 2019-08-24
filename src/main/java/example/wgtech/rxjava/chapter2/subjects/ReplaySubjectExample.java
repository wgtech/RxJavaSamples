package example.wgtech.rxjava.chapter2.subjects;

import io.reactivex.subjects.ReplaySubject;

/**
 *  ReplaySubject
 *  - Subject 클래스의 목적은 Hot Observable을 활용하기 위함이지만,
 *    이 ReplaySubject는 Cold Observable 처럼 동작한다.
 *  - 구독자가 새로 생기면 '항상' 데이터의 '처음부터 끝까지' 발행한다.
 *  - 모든 데이터 내용을 저장해두는 과정 중 메모리 누수가 발생할 가능성을 염두에 둬야한다. 따라서 사용에 주의해야한다.
 */
public class ReplaySubjectExample {

    private void marbleDiagram() {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data)); // 구독 시작시 1, 3, 5 모든 발행 값을 얻는다.
        subject.onNext("5");
        subject.onComplete();
    }


    public static void main(String[] args) {
        ReplaySubjectExample replay = new ReplaySubjectExample();
        replay.marbleDiagram();
    }
}
