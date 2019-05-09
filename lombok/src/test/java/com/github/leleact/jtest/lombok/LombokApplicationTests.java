package com.github.leleact.jtest.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LombokApplicationTests {

    @Test
    public void showLogTest() {
        log.debug("debug log");
        log.info("info log");
    }
}
