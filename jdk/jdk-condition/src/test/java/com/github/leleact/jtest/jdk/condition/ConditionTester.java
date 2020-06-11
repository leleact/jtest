package com.github.leleact.jtest.jdk.condition;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionTester {

    /**
     * 只有一个通知，多次通知只响应一次
     *
     * @throws InterruptedException
     */
    @Test
    public void conditionTest() throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();

        final Condition condition = lock.newCondition();

        Thread t = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    boolean ret = condition.await(10, TimeUnit.SECONDS);
                    if (ret) {
                        log.info("awake");
                    } else {
                        log.info("time out");
                    }
                } catch (InterruptedException e) {
                    log.info(e.getMessage(), e);
                } finally {
                    lock.unlock();
                }
            }
        });

        t.start();

        lock.lock();
        log.info("signal 1");
        condition.signal();
        lock.unlock();

        Thread.sleep(1000L);

        lock.lock();
        log.info("signal 2");
        condition.signal();
        lock.unlock();

        Thread.sleep(20000L);
    }
}
