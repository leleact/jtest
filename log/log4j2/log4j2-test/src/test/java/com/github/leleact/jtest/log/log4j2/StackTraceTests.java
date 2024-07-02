package com.github.leleact.jtest.log.log4j2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackTraceTests {
    private static final Logger log = LoggerFactory.getLogger(StackTraceTests.class);

    @Test
    public void longStackTraceLogOutputTest() {
        try {
            printNPE(0, 1000);
        } catch (Throwable e) {
            log.error("an error occured", e);
        }
    }

    private void printNPE(int cur, int stackDepth) {
        if (cur >= stackDepth) {
            throw new NullPointerException();
        }
        printNPE(cur + 1, stackDepth);
    }
}
