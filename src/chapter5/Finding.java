package chapter5;

import chapter4.Dish;

import static chapter4.Dish.menu;

public class Finding {
    public static void main(String[] args) {

    }

    //프레디케이트가 적어도 한 요소와 일치하는지 확인
    private static boolean isVegetarianFriendlyMenu() {
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    //프레디케이트가 모든 요소와 일치하는지 확인
    private static boolean isHealthyMenu() {
        return menu.stream().allMatch(dish -> dish.getCalories() < 1000);
    }

}
