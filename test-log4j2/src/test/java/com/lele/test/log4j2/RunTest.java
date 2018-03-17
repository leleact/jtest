package com.lele.test.log4j2;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunTest {

    private static Logger log = LoggerFactory.getLogger(RunTest.class);

    @Test
    public void log4j2Test() {
        log.trace("trace");
        log.info("info");
        log.debug("debug");
        log.warn("warn");
        log.error("error");
    }
}