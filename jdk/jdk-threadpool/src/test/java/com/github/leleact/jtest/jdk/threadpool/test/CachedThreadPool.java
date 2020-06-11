package com.github.leleact.jtest.jdk.threadpool.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CachedThreadPool {

    @Test
    public void threadNumTest() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 4, 10L, TimeUnit.MICROSECONDS,
            new SynchronousQueue<Runnable>());

        for (int i = 0; i < 100000; i++) {
            try {
                threadPoolExecutor.submit(new Runnable() {
                    @Override
                    public void run() {
                        int j = atomicInteger.getAndIncrement();
                        log.info("" + j);
                    }
                });
            } catch (RejectedExecutionException e) {
            }
        }
    }
}
