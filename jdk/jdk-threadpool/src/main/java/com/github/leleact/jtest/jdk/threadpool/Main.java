package com.github.leleact.jtest.jdk.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Main {

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(2, Integer.MAX_VALUE, 60,
        TimeUnit.SECONDS, new SynchronousQueue<>(true), new ThreadFactory() {
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            //thread.setDaemon(true);
            thread.setName("customer-thread-" + threadNumber.getAndIncrement());
            return thread;
        }
    }, new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.info("rejected");
        }
    });

    public static void main(String[] args) throws InterruptedException {
        int count = 10;
        CountDownLatch cl = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            EXECUTOR.execute(() -> {
                log.info("start");
                try {
                    TimeUnit.SECONDS.sleep(86400L);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
                cl.countDown();
                log.info("end");
            });
        }
        cl.await();
        EXECUTOR.shutdown();// invoke shutdown or set thread to daemon
    }
}
