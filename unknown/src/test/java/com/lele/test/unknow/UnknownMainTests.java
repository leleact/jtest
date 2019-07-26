package com.lele.test.unknow;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class UnknownMainTests {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void mainTest() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.countDown();
    }
}
