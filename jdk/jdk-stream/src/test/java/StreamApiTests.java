import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * jdk stream api test
 *
 * @author leleact
 * @since 2021-09-15
 */
public class StreamApiTests {
    @Test
    public void mapToObjTest() {
        Assertions.assertArrayEquals(new String[]{"1", "2", "3"}, IntStream.of(1, 2, 3)
                                                                           .mapToObj(String::valueOf)
                                                                           .toArray());
    }

    @Test
    public void integerListToIntArrayTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        // Integer list convert to int array
        Assertions.assertArrayEquals(new int[]{1, 2, 3}, list.stream().mapToInt(Integer::valueOf).toArray());
    }
}
