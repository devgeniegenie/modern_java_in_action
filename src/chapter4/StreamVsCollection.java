package chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVsCollection {
    public static void main(String[] args) {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
//        s.forEach(System.out::println); 스트림이 이미 소비되었거나 닫혀서 java.lang.IllegalStateException
    }
}
