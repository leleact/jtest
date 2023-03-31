package com.github.leleact.jtest.jdk.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * ForkJoinPool  test
 *
 * @author leleact
 * @since 2023-03-31
 */
@Slf4j
public class ForkJoinPoolTests {
    @Test
    public void forkJoinPoolTest() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        log.info("poolSize: {}", pool.getPoolSize());
        log.info("parallelism: {}", pool.getParallelism());
        String[] strings = {"a", "b", "c"};
        try {
            pool.invokeAll(Arrays.stream(strings).map(s -> (Callable<Void>) () -> {
                Thread.sleep(10);
                log.info("accept string: {}", s);
                return null;
            }).collect(Collectors.toList()), 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void streamParallelTest() {
        String[] strings = {"a", "b", "c"};
        Arrays.asList(strings).parallelStream().forEach(s -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("accept string: {}", s);
        });
    }
}
