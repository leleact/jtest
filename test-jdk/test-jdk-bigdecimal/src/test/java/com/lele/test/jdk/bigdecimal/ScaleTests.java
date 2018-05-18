package com.lele.test.jdk.bigdecimal;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ScaleTests {

    @Test
    public void scaleTest() {
        String ps = "3.1415926";
        String ns = "-3.1415826";

        Assert.assertEquals("3.15", new BigDecimal(ps).setScale(2, BigDecimal.ROUND_UP).toString());
        Assert.assertEquals("-3.15", new BigDecimal(ns).setScale(2, BigDecimal.ROUND_UP).toString());

        Assert.assertEquals("3.14", new BigDecimal(ps).setScale(2, BigDecimal.ROUND_DOWN).toString());
        Assert.assertEquals("-3.14", new BigDecimal(ns).setScale(2, BigDecimal.ROUND_DOWN).toString());

        Assert.assertEquals("3.15", new BigDecimal(ps).setScale(2, BigDecimal.ROUND_CEILING).toString());
        Assert.assertEquals("-3.14", new BigDecimal(ns).setScale(2, BigDecimal.ROUND_CEILING).toString());

        Assert.assertEquals("3.14", new BigDecimal(ps).setScale(2, BigDecimal.ROUND_FLOOR).toString());
        Assert.assertEquals("-3.15", new BigDecimal(ns).setScale(2, BigDecimal.ROUND_FLOOR).toString());

        Assert.assertEquals("3.14", new BigDecimal(ps).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        Assert.assertEquals("3.142", new BigDecimal(ps).setScale(3, BigDecimal.ROUND_HALF_UP).toString());
        Assert.assertEquals("-3.14", new BigDecimal(ns).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        Assert.assertEquals("-3.142", new BigDecimal(ns).setScale(3, BigDecimal.ROUND_HALF_UP).toString());
    }
}
