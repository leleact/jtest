package com.lele.test.log4j2;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog4j2ApplicationTests {

//    static {
//        System.setProperty("log4j2.skipJansi", "false");
//    }

    private static final Logger log = LoggerFactory.getLogger(TestLog4j2ApplicationTests.class);

    private static final Logger anotherLogger = LoggerFactory.getLogger("com.another");

    @Test
    public void log4j2Test() {


        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");

        anotherLogger.trace("trace");
        anotherLogger.debug("debug");
        anotherLogger.info("info");
        anotherLogger.warn("warn");
        anotherLogger.error("error");
    }
}
