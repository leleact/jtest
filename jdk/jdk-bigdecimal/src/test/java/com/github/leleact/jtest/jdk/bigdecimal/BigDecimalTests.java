package com.github.leleact.jtest.jdk.bigdecimal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@Slf4j
class BigDecimalTests {

    @Test
    void moveleftPointTest() {
        Assertions.assertEquals("1", new BigDecimal("0.01").movePointRight(2).toString());
        Assertions.assertEquals("100", new BigDecimal("1").movePointRight(2).toString());
    }
}
