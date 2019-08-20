package com.github.leleact.test.spring.profiles;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraceLogTests {

    private static final Logger logger = LoggerFactory.getLogger(TraceLogTests.class);

    @Test
    public void printLog() {
        if (logger.isTraceEnabled()) {
            logger.trace("xxx");
        }
    }
}
