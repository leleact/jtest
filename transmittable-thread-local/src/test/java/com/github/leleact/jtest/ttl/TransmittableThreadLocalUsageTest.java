package com.github.leleact.jtest.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TransmittableThreadLocal (阿里 TTL) 使用示例。
 *
 * <p>解决 InheritableThreadLocal 在线程池复用场景下上下文丢失的问题。</p>
 */
class TransmittableThreadLocalUsageTest {

    private static final Logger log = LoggerFactory.getLogger(TransmittableThreadLocalUsageTest.class);

    @Test
    @DisplayName("InheritableThreadLocal 在线程池复用场景失效")
    void inheritableThreadLocal_breaksWithPool() throws Exception {
        InheritableThreadLocal<String> ctx = new InheritableThreadLocal<>();
        ExecutorService pool = Executors.newFixedThreadPool(1);

        ctx.set("A");
        assertEquals("A", pool.submit(ctx::get).get(), "首次提交正确");

        ctx.set("B");
        String result = pool.submit(ctx::get).get();
        assertNotEquals("B", result, "线程池复用导致值错乱");

        pool.shutdown();
    }

    @Test
    @DisplayName("TtlCallable 包装解决线程池问题")
    void ttlCallable_wrap() throws Exception {
        TransmittableThreadLocal<String> ctx = new TransmittableThreadLocal<>();
        ExecutorService pool = Executors.newFixedThreadPool(1);

        ctx.set("A");
        assertEquals("A", pool.submit(ctx::get).get());

        ctx.set("B");
        Callable<String> task = () -> ctx.get();
        Future<String> f2 = pool.submit(com.alibaba.ttl.TtlCallable.get(task));
        assertEquals("B", f2.get(), "TtlCallable 解决了线程池复用问题");

        pool.shutdown();
    }

    @Test
    @DisplayName("TtlExecutors 包装线程池，自动传递上下文（推荐）")
    void ttlExecutors_wrap() throws Exception {
        TransmittableThreadLocal<String> ctx = new TransmittableThreadLocal<>();
        ExecutorService pool = TtlExecutors.getTtlExecutorService(
                Executors.newFixedThreadPool(2));

        ctx.set("trace-001");

        Future<String> rpc1 = pool.submit(() -> "RPC-1 traceId=" + ctx.get());
        Future<String> rpc2 = pool.submit(() -> "RPC-2 traceId=" + ctx.get());

        assertEquals("RPC-1 traceId=trace-001", rpc1.get());
        assertEquals("RPC-2 traceId=trace-001", rpc2.get());

        ctx.set("trace-002");
        Future<String> rpc3 = pool.submit(() -> "RPC-3 traceId=" + ctx.get());
        assertEquals("RPC-3 traceId=trace-002", rpc3.get(),
                "TTL 确保新任务拿到新值，即使线程被复用");

        pool.shutdown();
    }

    @Test
    @DisplayName("多个 TransmittableThreadLocal 同时传递")
    void multipleTTLs() throws Exception {
        TransmittableThreadLocal<String> userId = new TransmittableThreadLocal<>();
        TransmittableThreadLocal<String> tenantId = new TransmittableThreadLocal<>();
        ExecutorService pool = TtlExecutors.getTtlExecutorService(
                Executors.newFixedThreadPool(1));

        userId.set("u-001");
        tenantId.set("t-001");

        Future<String> f1 = pool.submit(() -> userId.get() + " / " + tenantId.get());
        assertEquals("u-001 / t-001", f1.get());

        userId.set("u-002");
        Future<String> f2 = pool.submit(() -> userId.get() + " / " + tenantId.get());
        assertEquals("u-002 / t-001", f2.get(), "仅修改的值变化");

        pool.shutdown();
    }

    @Test
    @DisplayName("TransmittableThreadLocal 保留 InheritableThreadLocal 的继承能力")
    void inheritable_behavior() throws Exception {
        TransmittableThreadLocal<String> ctx = new TransmittableThreadLocal<>();
        ctx.set("parent-value");

        AtomicReference<String> childValue = new AtomicReference<>();
        Thread child = new Thread(() -> {
            childValue.set(ctx.get());
            ctx.set("child-modify");
        });
        child.start();
        child.join();

        assertEquals("parent-value", childValue.get(), "子线程自动继承");
        assertEquals("parent-value", ctx.get(), "子线程修改不影响父线程");
    }

    @Test
    @DisplayName("TTL 局限：CompletableFuture 需要手动传 Executor")
    void ttl_limitation() {
        TransmittableThreadLocal<String> ctx = new TransmittableThreadLocal<>();
        ctx.set("ctx");

        ExecutorService ttlPool = TtlExecutors.getTtlExecutorService(
                Executors.newSingleThreadExecutor());
        CompletableFuture<String> fixed = CompletableFuture.supplyAsync(ctx::get, ttlPool);
        assertEquals("ctx", fixed.join(), "传入 TTL executor 后正确传递");
        ttlPool.shutdown();
    }
}
