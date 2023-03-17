package chapter6;

//import java.util.stream.Collectors;

import chapter4.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.function.BinaryOperator;

import static chapter4.Dish.menu;
import static java.util.stream.Collectors.*;

public class Summarizing {
    public static void main(String[] args) {

    }

    private static long howManyDishes() {
        return menu.stream().collect(counting());
    }

    /*private static Dish findMostCaloricDishUsingComparator() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        return menu.stream().collect(reducing(moreCaloricOf));
    }*/

    private static int totalCalories() {
        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    private static double avgCalories() {
        return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics() {
        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    private static String getShortMenu() {
        return menu.stream().map(Dish::getName).collect(joining());
    }

    private static String getShortMenuCommaSeparated() {
        return menu.stream().map(Dish::getName).collect(joining(", "));
    }
}
