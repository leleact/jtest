package com.github.leleact.jtest.logback.logger;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logger test.
 *
 * @author leleact
 * @since 1.0
 */
@Slf4j
class LoggerTests {

    private static final Logger A1 = LoggerFactory.getLogger(LoggerTests.class.getName() + ".A1");

    @Test
    void loggerTest() {
        A1.trace("t"); // non output
        A1.debug("d");
        A1.info("i");
        A1.warn("w");

        log.trace("T"); // output
        log.debug("D");
        log.info("I");
        log.warn("W");
    }

}
