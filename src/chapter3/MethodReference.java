package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

import static java.lang.Integer.compare;
import static java.util.Comparator.comparing;

public class MethodReference {

    /**
     * 메서드 참조
     */
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );

        ToIntFunction<String> stringToInt = (String s) -> Integer.parseInt(s);
        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
//        Predicate<String> startWithnumber = (String string) -> this.startWithNumber(string);
        inventory.sort((Apple a1, Apple a2) -> compare(a1.getWeight(),(a2.getWeight())));

        ToIntFunction<String> stringToInt2 = Integer::parseInt;
        BiPredicate<List<String>, String> contains2 = List::contains;
//        Predicate<String> startWithnumber2 = (String string) -> this::startWithNumber;
        inventory.sort(comparing(Apple::getWeight));

        /**
         * 생성자 참조
         */
        Supplier<Apple> c1 = () -> new Apple();
        Supplier<Apple> c2 = Apple::new;
        Apple a1 = c2.get();

        Function<Integer, Apple> c3 = Apple::new;
        Apple a2 = c3.apply(110);

        List<Integer> weights = Arrays.asList(7, 5, 6, 11);
        List<Apple> apples = map(weights, Apple::new);

        BiFunction<Color, Integer, Apple> c4 = Apple::new;
        Apple tmp1 = c4.apply(Color.GREEN, 100);
        BiFunction<Color, Integer, Apple> c5 = (color, weight) -> new Apple(color, weight);
        Apple tmp2 = c5.apply(Color.GREEN, 100);

    }
    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(f.apply(i));
        }
        return result;
    }
}
