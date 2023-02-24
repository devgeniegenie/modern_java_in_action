package chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class StreamBasic {
    public static void main(String[] args) {
        // 자바 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // 자바 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        List<String> lowCaloricDishesNames = new ArrayList<String>();
        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesNames.add(dish.getName());
        }
        return lowCaloricDishesNames;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)
//                .sorted((a, b) -> Integer.compare(a.getCalories(), b.getCalories()))
                .sorted(comparing(Dish::getCalories))
//                .map(d -> d.getName())
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

}
