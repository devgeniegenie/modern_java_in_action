package chapter5;

import chapter4.Dish;

import java.util.Arrays;
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

        //숫자범위
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n%2 == 0);
        System.out.println(evenNumbers.count());

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        pythagoreanTriples.limit(5).forEach(n -> System.out.println(n[0] + " " + n[1] + " " +  n[2]));

        /*System.out.println(1 + ' ' + 2);
        System.out.print(1 + ' ' + 2);*/

        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples2.limit(5).forEach(n -> System.out.println(n[0] + " " + n[1] + " " +  n[2]));

        Stream<int[]> pythagoreanTriples3 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0))
                .map(arr -> Arrays.stream(arr).mapToInt(a -> (int) a).toArray());


    }
}
