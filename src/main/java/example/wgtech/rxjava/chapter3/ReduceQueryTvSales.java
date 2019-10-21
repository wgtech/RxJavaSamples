package example.wgtech.rxjava.chapter3;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO class과 다른 방식.
 * - 사용자 정의 클래스가 보이지 않는다.
 * (!) 자료구조인 클래스를 새로 만들기 보다, Pair 혹은 Tuple 같은 좀 일반화된 자료구조를 선호한다.
 */
public class ReduceQueryTvSales {

    private void run() {
        List<Pair<String, Integer>> sales = new ArrayList<>();

        // 1. 데이터 입력 (상품 이름, 매출액 순)
        sales.add(Pair.of("TV", 2500));
        sales.add(Pair.of("Camera", 300));
        sales.add(Pair.of("TV", 1600));
        sales.add(Pair.of("Phone", 800));

        Maybe<Integer> tvSales = Observable.fromIterable(sales)
                                    // 2. 매출 데이터 중 TV 매출을 필터링함
                                    .filter(sale -> "TV".equals(sale.getLeft())) // Pair의 L 부분이 TV인 것을 ㅍ
                                    .map(sale -> sale.getRight()) // 가격으로 매핑

                                    // 3. TV 매출의 합을 구함
                                    .reduce((sale1, sale2) -> sale1 + sale2);
    }

    public static void main(String[] args) {
        ReduceQueryTvSales reduceQueryTvSales = new ReduceQueryTvSales();

        reduceQueryTvSales.run();
    }
}
