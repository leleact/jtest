package com.github.leleact.jtest.spring.schedule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Data
public class Job implements Runnable {

    private AtomicBoolean enabled = new AtomicBoolean(true);

    private AtomicInteger counter = new AtomicInteger(0);

    private String name;

    public Job(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (enabled.get()) {
            log.info("running job [{}]...", name);
            try {
                Thread.sleep(300L);
                counter.incrementAndGet();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
