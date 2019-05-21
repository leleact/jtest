package com.lele.test.bigdecimal;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    @Test
    public void test1() {
        {
            BigDecimal bigDecimal = new BigDecimal("78293578923");
            log.debug("" + bigDecimal);
        }

        {
            BigDecimal bigDecimal = new BigDecimal("100093.00454");
            log.debug("" + bigDecimal);
        }

        {
            BigDecimal bigDecimal = new BigDecimal("78293823746598237465982374605872369458762349857578923");
            log.debug("" + bigDecimal);
        }

    }

}
