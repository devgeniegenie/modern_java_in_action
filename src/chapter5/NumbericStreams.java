package chapter5;

import chapter4.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static chapter4.Dish.menu;

public class NumbericStreams {
    public static void main(String[] args) {
        //박싱비용 발생 : 내부적으로 합계를 계산하기 전에 Integer를 기본으로 언박싱해야함
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);

        int calories2 = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories); //스트림을 숫자 스트림으로 변환
        Stream<Integer> stream = intStream.boxed(); //숫자 스트림을 스트림으로 변환

        //IntStream에서 최댓값을 찾을때 기본값 0때문에 잘못된 결과 나올수 있음 > OptionalInt이용
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1); //값이 없을 때 기본 최댓값을 명시적으로 설정
    }
}
