package com.github.leleact.jtest.jdk.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * completable future tests
 *
 * @author leleact
 * @since 2023-07-14
 */
@Slf4j
public class CompletableFutureTests {
    private final Executor executor = Executors.newFixedThreadPool(4);

    @Test
    public void setTimeoutTest() throws ExecutionException, InterruptedException, TimeoutException {
        log.info("Start async job.");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 2;
        }, executor).whenComplete((v, e) -> {
            log.info("Print value of async job: {}.", v);
        });
        Integer value = future.get(2, TimeUnit.SECONDS);
        Assertions.assertEquals(2, value);
        log.info("End async job.");
    }

    @Test
    public void whenCompleteTest() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 1, executor).whenComplete((r, t) -> {
            log.info("Print value of async job: {}.", r);
            Assertions.assertEquals(1, r);
        }).whenComplete((r, t) -> {
            log.info("Print value of async job: {}.", r);
            Assertions.assertEquals(1, r);
        });
        future.join();
    }

    @Test
    public void combinedTest() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        }, executor);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 2;
        }, executor);

        CompletableFuture<Void> future = CompletableFuture.allOf(future1, future2).whenComplete((v, e) -> {
            if (e != null) {
                throw new RuntimeException(e);
            } else {
                log.info("Print value of combined job: {}.", v);
                Assertions.assertNull(v);
            }
        });

        future.join();
    }
}
