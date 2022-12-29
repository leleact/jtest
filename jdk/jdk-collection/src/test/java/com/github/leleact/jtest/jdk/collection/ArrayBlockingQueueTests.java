package com.github.leleact.jtest.jdk.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

/**
 * ArrayBlockingQueue test.
 *
 * @author leleact
 * @since 1.0
 */
@Slf4j
class ArrayBlockingQueueTests {

    static class Future<V> {

        private V value;

        CountDownLatch waiter = new CountDownLatch(1);

        V get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
            if (!waiter.await(timeout, unit)) {
                throw new TimeoutException("交易处理超时");
            }
            return value;
        }

        void setValue(V value) {
            this.value = value;
            waiter.countDown();
        }
    }

    static class Worker implements Runnable {

        boolean indicator = true;

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(200);

        ConcurrentMap<Integer, Future<Integer>> futures = new ConcurrentHashMap<>();

        AtomicInteger counter = new AtomicInteger(0);

        Future<Integer> add(Integer i) throws InterruptedException {
            Future<Integer> future = new Future<>();
            futures.put(i, future);
            queue.add(i);
            // notifyAll 应该放在后面, 放在前面并发满队列后，没有通知
            if (queue.size() + 1 >= 200) {
                synchronized (this) {
                    this.notifyAll();
                }
            }
            return future;
        }

        void stop() {
            indicator = false;
        }

        @Override
        public void run() {
            while (indicator) {
                try {
                    synchronized (this) {
                        while (queue.isEmpty()) {
                            this.wait(100L);
                        }
                    }
                    List<Integer> collection = new ArrayList<>(200);
                    queue.drainTo(collection);
                    log.debug("Size: {}", collection.size());
                    Thread.sleep(100L);
                    collection.forEach((i) -> {
                        Future<Integer> f = futures.remove(i);
                        if (f != null) {
                            f.setValue(i);
                        } else {
                            log.warn("id [{}] not exist", i);
                        }
                        counter.getAndIncrement();
                    });
                    log.debug("Counter: {}", counter.get());
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    @Test
    void drainToTest() throws InterruptedException {
        Worker w = new Worker();
        Thread t = new Thread(w);
        t.start();

        final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(200);
        ExecutorService executorService = new ThreadPoolExecutor(5, 200,
            60L, TimeUnit.SECONDS,
            queue, (Runnable r, ThreadPoolExecutor executor) -> {
            try {
                queue.put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        int counter = 10000;
        for (int i = 0; i < counter; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(10L);
                    Future<Integer> r = w.add(finalI);
                    r.get(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                } catch (TimeoutException e) {
                    log.debug("i = {} 超时", finalI);
                    log.warn(e.getLocalizedMessage(), e);
                }
            });
        }
        await().atMost(5, TimeUnit.MINUTES).untilAtomic(w.counter, equalTo(10000));
        w.stop();
    }
}
