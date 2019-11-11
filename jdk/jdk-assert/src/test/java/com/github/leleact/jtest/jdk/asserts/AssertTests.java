package com.github.leleact.jtest.jdk.asserts;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class AssertTests {

    @Test
    public void assertTest() {
        Assertions.assertThrows(AssertionError.class, () -> {
            int i = 2;
            assert i == 3;
        });
    }
}
