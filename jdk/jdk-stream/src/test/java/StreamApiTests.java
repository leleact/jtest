import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
