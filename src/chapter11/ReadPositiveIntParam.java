package chapter11;

import java.util.Optional;

import org.junit.Test;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class ReadPositiveIntParam {
    public static Optional<Integer> strinigToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }


    @Test
    public void testMap() {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

        assertEquals(5, readDurationImperative(props, "a"));
        assertEquals(0, readDurationImperative(props, "b"));
        assertEquals(0, readDurationImperative(props, "c"));
        assertEquals(0, readDurationImperative(props, "d"));

        assertEquals(5, readDurationWithOptional(props, "a"));
        assertEquals(0, readDurationWithOptional(props, "b"));
        assertEquals(0, readDurationWithOptional(props, "c"));
        assertEquals(0, readDurationWithOptional(props, "d"));
    }

    private int readDurationImperative(Properties props, String name) {
        String value = props.getProperty(name);
        if(value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException e) {

            }
        }
        return 0;
    }

    private int readDurationWithOptional(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(ReadPositiveIntParam::strinigToInt)
                .filter(i -> i < 0)
                .orElse(0);
    }
}
