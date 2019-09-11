package com.github.leleact.jtest.log4j2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerformanceTests {

    private static final Logger logA = LoggerFactory.getLogger("A");

    private static final Logger logB = LoggerFactory.getLogger("B");

    private static final Logger root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    private static final int count = 100000;

    @Test
    public void methodTest() {

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            logB.debug("XXXX");
        }

        root.info("Has Method Escape time: [{}]ms", System.currentTimeMillis() - start);
    }

    @Test
    public void noMethodTest() {

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            logA.debug("XXXX");
        }

        root.info("No Method Escape time: [{}]ms", System.currentTimeMillis() - start);
    }

}
