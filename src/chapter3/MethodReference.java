package chapter3;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

import static java.lang.Integer.compare;
import static java.util.Comparator.comparing;

public class MethodReference {

    /*
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
    }
}
