package example.wgtech.rxjava.chapter2.subjects;

import io.reactivex.subjects.PublishSubject;

/**
 *  PublishSubject
 *  - 가장 평범한 Subject 클래스
 *  - 구독자가 subscribe() 함수를 호출하면 값을 발행하기 시작한다.
 *  - AsyncSubject 클래스처럼 마지막 값만 발행하거나 BehaviorSubject 클래스처럼 발행한 값이 없을 때 기본값을 대신 발행하지도 않는다.
 *  - 해당 시간에 발생한 데이터를 그대로 구독자에게 전달받는다.
 */
public class PublishSubjectExample {

    private void marbleDiagram() {
        // BehaviorSubject 와는 달리 별도의 기본값을 제공해줄 필요는 없으므로, create() 함수를 호출해 생성한다.
        PublishSubject<String> subject = PublishSubject.create(); // 기본값 제공할 의무가 없다.
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data)); // 첫 구독자가 subscribe() 호출
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data)); // 다른 구독자가 subscribe() 호출, 앞서 추가된 값 1과 3은 받지 않는다.
        subject.onNext("5");
        subject.onComplete();
    }


    public static void main(String[] args) {
        PublishSubjectExample publish = new PublishSubjectExample();
        publish.marbleDiagram();
    }
}
