package com.github.leleact.jtest.spring.context;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

/**
 * thread pool task
 *
 * @author leleact
 * @since 2024-01-19
 */
@Slf4j
public class ThreadPoolTaskExecutorTests {

    private static final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    @BeforeAll
    public static void init() {
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("Jtest-threadpool-exec-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setTaskDecorator(new TaskDecorator() {
            @Override
            public Runnable decorate(Runnable runnable) {
                return new Runnable() {
                    private Map<String, String> context = MDC.getCopyOfContextMap();

                    @Override
                    public void run() {
                        MDC.setContextMap(context);
                        try {
                            log.info("runnable start");
                            runnable.run();
                            log.info("runnable end");
                        } finally {
                            MDC.clear();
                        }
                    }
                };
            }
        });
        executor.initialize();
    }

    @Test
    public void decoratorRunnableTest() {
        MDC.put("aaa", "111");
        MDC.put("bbb", "222");
        log.info("Main");
        CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                log.info("11111111111111111111");
                return "ok";
            }
        }, executor).whenComplete((r, t) -> {
            if (t == null) {
                log.info("result: {}", r);
            } else {
                log.error("err", t);
            }
        });
    }
}
