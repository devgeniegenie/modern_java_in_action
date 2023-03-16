package chapter5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {
    public static void main(String[] args) {
        //Stream.of
        Stream<String> stream = Stream.of("Mordern ", "Java ", "In ", "Action ");
        stream.map(String::toUpperCase).forEach(System.out::println);

        //Stream.empty
        Stream<String> emptyStream = Stream.empty();

        //Stream.ofNullable
        String homeValue = System.getProperty("home");
        Stream<String> homeValuesStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);

        Stream<String> homeValueStream2 = Stream.ofNullable(System.getProperty("home"));

        Stream<String> values =
                Stream.of("config", "home", "user")
                        .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        //Arrays.stream
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        //Files.lines
        long uniqueWords = 0;

        try (Stream<String> lines =
                     Files.lines(Paths.get("C:\\toy\\modernJavaInAction\\src\\chapter5\\data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {

        }

        //Stream.iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        //iterate 응용한 피보나치
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(10)
//                .forEach(t -> System.out.println(t[0] + ", " + t[1]))
                .map(t -> t[0])
                .forEach(System.out::println);

        IntStream.iterate(0, n -> n < 100, n -> n + 4).forEach(System.out::println);
        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);

        //Stream.generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        IntStream ones = IntStream.generate(() -> 1);
        IntStream tows = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
