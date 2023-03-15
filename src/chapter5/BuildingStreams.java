package chapter5;

import java.util.stream.Stream;

public class BuildingStreams {
    public static void main(String[] args) {
        //Stream.of
        Stream<String> stream = Stream.of("Mordern ", "Java ", "In ", "Action ");
        stream.map(String::toUpperCase).forEach(System.out::println);

        //Stream.empty
        Stream<String> emptyStream = Stream.empty();


    }
}
