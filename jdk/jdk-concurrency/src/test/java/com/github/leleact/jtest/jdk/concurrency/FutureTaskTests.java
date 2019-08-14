package com.github.leleact.jtest.jdk.concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

class FutureTaskTests {

    private static ExecutorService executorService;

    @BeforeAll
    static void setup() {
        executorService = Executors.newSingleThreadExecutor();
    }

    @Test
    void futureTest() throws ExecutionException, InterruptedException {
        Future<String> future = executorService.submit(() -> "ok");
        Assertions.assertEquals("ok", future.get());
    }
}
