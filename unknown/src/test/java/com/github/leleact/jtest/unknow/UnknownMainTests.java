package com.github.leleact.jtest.unknow;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
class UnknownMainTests {

    @Test
    void mainTest() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.countDown();
    }
}
