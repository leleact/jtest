package com.lele.test.jdk.bigdecimal;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class BigDecimalTests {

    private static final Logger log = LoggerFactory.getLogger(BigDecimalTests.class);

    @Test
    public void moveleftPointTest() {
        Assert.assertEquals("1", new BigDecimal("0.01").movePointRight(2).toString());
        Assert.assertEquals("100", new BigDecimal("1").movePointRight(2).toString());
    }
}
