package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lambda {

    /*
     * Predicate
     */
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    List<String> listOfStrings = Arrays.asList("a", "b", "c");

    Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
    List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);

    /*
     * Consumer
     */
    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

    public <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }
    /*forEach(Arrays.asList(1, 2, 3, 4),
                (Integer i) -> System.out.println(i));*/

    /*
     * Function
     */
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    public <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    List<Integer> l = map(
            Arrays.asList("lambdas", "in", "action"),
            (String s) -> s.length()
    );

}
