package chapter4;

import java.util.List;
import java.util.stream.Collectors;

import static chapter4.Dish.menu;

public class StreamSout {
    public static void main(String[] args) {
        //출력 어떤식으로 되는지 확인 > 데이터 처리과정 확인
        List<String> names = menu.stream()
                .filter(dish -> {
                    System.out.println("filtering: " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping: " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);
    }
}
