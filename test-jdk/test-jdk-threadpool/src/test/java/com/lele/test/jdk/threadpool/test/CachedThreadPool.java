package com.lele.test.jdk.threadpool.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CachedThreadPool {

    private static final Logger log = LoggerFactory.getLogger(CachedThreadPool.class);

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
