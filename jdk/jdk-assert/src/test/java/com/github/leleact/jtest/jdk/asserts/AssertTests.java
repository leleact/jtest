package com.github.leleact.jtest.jdk.asserts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertTests {

    @Test
    public void assertTest() {
        Assertions.assertThrows(AssertionError.class, () -> {
            int i = 2;
            assert i == 3;
        });
    }
}
