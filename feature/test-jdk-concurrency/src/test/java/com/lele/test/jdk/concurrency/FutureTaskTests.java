package com.lele.test.jdk.concurrency;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

public class FutureTaskTests {

    private ExecutorService executorService;

    @Before
    public void setup() {
        executorService = Executors.newSingleThreadExecutor();
    }

    @Test
    public void futureTest() throws ExecutionException, InterruptedException {

        Future<String> future = executorService.submit(() -> "ok");

        String str = future.get();
    }
}
