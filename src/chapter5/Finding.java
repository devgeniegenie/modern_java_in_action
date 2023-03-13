package chapter5;

import chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static chapter4.Dish.menu;

public class Finding {
    public static void main(String[] args) {
        System.out.println(isVegetarianFriendlyMenu());
        System.out.println(isHealthyMenu());
        System.out.println(firstSquareDivisibleByTree(Arrays.asList(1,2)));

    }

    //프레디케이트가 적어도 한 요소와 일치하는지 확인
    private static boolean isVegetarianFriendlyMenu() {
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    //프레디케이트가 모든 요소와 일치하는지 확인
    private static boolean isHealthyMenu() {
        return menu.stream().allMatch(dish -> dish.getCalories() < 1000);
    }

    //프레디케이트와 일치하는 요소가 없는지 확인
    private static boolean isHealthyMenu2() {
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    private static Optional<Dish> findVegetarianDish() {
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }

    private static Optional<Integer> firstSquareDivisibleByTree(List<Integer> someNumbers) {
        return someNumbers.stream().map(n -> n * n).filter(n -> n % 3 == 0).findFirst();
    }
}
