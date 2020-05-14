package com.github.leleact.jtest.log4j2;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoDeleteTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoDeleteTests.class);

    @Test
    public void logTest() {
        LOGGER.debug("aaa");
        LOGGER.debug("bbb");
        LOGGER.debug("ccc");
    }
}
