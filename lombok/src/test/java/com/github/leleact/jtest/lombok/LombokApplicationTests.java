package com.github.leleact.jtest.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
class LombokApplicationTests {

    @Test
    void showLogTest() {
        log.debug("debug log");
        log.info("info log");
    }
}
