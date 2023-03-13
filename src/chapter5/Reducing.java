package chapter5;

import chapter4.Dish;

import java.util.Arrays;
import java.util.List;

import static chapter4.Dish.menu;

public class Reducing {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 3, 1, 2, 4);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum2 = numbers.stream().reduce(0, (Integer::sum));
        int product = numbers.stream().reduce(0, (a, b) -> a * b);
        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        int min = numbers.stream().reduce(0, (Integer::min));

        int count = menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
        System.out.println(menu.stream().count());
        System.out.println(menu.stream().map(Dish::getCalories).reduce(0, Integer::sum));
    }
}
