package example.wgtech.rxjava.chapter2.subjects;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

/**
 *  AsyncSubject
 *  - Observable에서 발행한 마지막 데이터를 얻어올 수 있는 Subject 클래스
 *  - 완료되기 전 마지막 데이터에만 관심이 있으며 이전 데이터는 무시한다.
 */
public class AsyncSubjectExample {

    private void marbleDiagram() {
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data)); // 구독 시작
        subject.onNext("1"); // 발행
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data)); // 두 번째 구독자가 호출
        subject.onNext("5");
        subject.onComplete(); // 마지막으로 입력된 데이터가 구독자에게 최종 전달
    }


    private void asSubscriber() {
        /**
         * 구독자로 동작하는 AsyncSubject 클래스
         * - AsyncSubject가 Observable의 구독자로 동작하는 예시
         */
        Float[] temperature = {10.1f, 13.4f, 12.5f};
        Observable<Float> source = Observable.fromArray(temperature);

        AsyncSubject<Float> subject = AsyncSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data)); // data = 12.5f 로 출력됨.

        source.subscribe(subject);

        // Subject 클래스가 Observable을 상속하고 동시에 Observer 인터페이스를 구현하기 때문이다.
    }


    private void afterComplete() {
        AsyncSubject<Integer> subject = AsyncSubject.create();
        subject.onNext(10);
        subject.onNext(11);
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data)); // 첫 구독자가 호출
        subject.onNext(12);
        subject.onComplete();
        subject.onNext(13); // 이 이벤트는 onComplete() 이후로 무시됨.
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data)); // 두 번째 구독자가 호출. onNext(13)이 무시당해 12로 출력된다.
        subject.subscribe(data -> System.out.println("Subscriber #3 => " + data)); // 세 번째 구독자가 호출. onNext(13)이 무시당해 12로 출력된다.
    }



    public static void main(String[] args) {
        AsyncSubjectExample async = new AsyncSubjectExample();
        async.marbleDiagram();
        async.asSubscriber();
        async.afterComplete();
    }
}
