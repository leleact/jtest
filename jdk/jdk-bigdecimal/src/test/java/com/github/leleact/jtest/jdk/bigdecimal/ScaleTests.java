package com.github.leleact.jtest.jdk.bigdecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

class ScaleTests {

    @Test
    void scaleTest() {
        String ps = "3.1415926";
        String ns = "-3.1415826";

        Assertions.assertEquals("3.15", new BigDecimal(ps).setScale(2, RoundingMode.UP).toString());
        Assertions.assertEquals("-3.15", new BigDecimal(ns).setScale(2, RoundingMode.UP).toString());

        Assertions.assertEquals("3.14", new BigDecimal(ps).setScale(2, RoundingMode.DOWN).toString());
        Assertions.assertEquals("-3.14", new BigDecimal(ns).setScale(2, RoundingMode.DOWN).toString());

        Assertions.assertEquals("3.15", new BigDecimal(ps).setScale(2, RoundingMode.CEILING).toString());
        Assertions.assertEquals("-3.14", new BigDecimal(ns).setScale(2, RoundingMode.CEILING).toString());

        Assertions.assertEquals("3.14", new BigDecimal(ps).setScale(2, RoundingMode.FLOOR).toString());
        Assertions.assertEquals("-3.15", new BigDecimal(ns).setScale(2, RoundingMode.FLOOR).toString());

        Assertions.assertEquals("3.14", new BigDecimal(ps).setScale(2, RoundingMode.HALF_UP).toString());
        Assertions.assertEquals("3.142", new BigDecimal(ps).setScale(3, RoundingMode.HALF_UP).toString());
        Assertions.assertEquals("-3.14", new BigDecimal(ns).setScale(2, RoundingMode.HALF_UP).toString());
        Assertions.assertEquals("-3.142", new BigDecimal(ns).setScale(3, RoundingMode.HALF_UP).toString());
    }
}
