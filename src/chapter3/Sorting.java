package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;

import static java.util.Comparator.comparing;

public class Sorting {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        ));
        //1
        inventory.sort(new AppleComparator());

        //2
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight() - a2.getWeight();
            }
        });

        //3
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
        inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());

        //1 > 2 > 3> 4 최종
        inventory.sort(comparing(Apple::getWeight));

        //etc
        inventory.sort(comparing(Apple::getWeight).reversed());
        inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));

        //predicate 조합

        //Function 조합
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println(h.apply(2));

        Function<Integer, Integer> c = f.compose(g);
        System.out.println(c.apply(2));

        //1차함수 적분
        //integrate(f, x1, x2)
        //integrate((double x) -> f(x), x1, x2)
        integrate((double x) -> x + 10, 3, 7);
    }

    public static class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight() - a2.getWeight();
        }
    }
    public static double integrate(DoubleFunction<Double> f, double x1, double x2) {
        return (f.apply(x1) + f.apply(x2)) * (x2 - x1) / 2.0;
    }

}
