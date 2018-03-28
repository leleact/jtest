package com.lele.test.unknow;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnknowMainTests {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void mainTest() {
        log.info("Hello World");
        log.error("Hello World");
    }
}
