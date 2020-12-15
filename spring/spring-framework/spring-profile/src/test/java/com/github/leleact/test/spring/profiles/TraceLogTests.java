package com.github.leleact.test.spring.profiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraceLogTests {

    private static final Logger logger = LoggerFactory.getLogger(TraceLogTests.class);

    @Test
    public void printLogTest() {
        if (logger.isTraceEnabled()) {
            logger.trace("xxx");
        }
        Assertions.assertEquals(1, 1);
    }
}
