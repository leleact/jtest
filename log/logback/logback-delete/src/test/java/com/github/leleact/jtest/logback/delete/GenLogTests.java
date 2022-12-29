package com.github.leleact.jtest.logback.delete;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;

/**
 * generate log.
 *
 * @author leleact
 * @since 1.0
 */
@Slf4j
class GenLogTests {

    @RepeatedTest(100)
    void genLogTest() {
        log.debug("1");
    }
}
