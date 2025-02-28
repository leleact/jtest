package com.github.leleact.jtest.micrometer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * micrometer test.
 *
 * @author leleact
 * @since 2024-09-11
 */
@Slf4j
public class CommonMeterTests {
    @Disabled
    @Test
    public void micrometerStartTest() {
        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
        MeterRegistry registry = new LoggingMeterRegistry();
        Counter counter = registry.counter("myCounter");
        Timer timer = registry.timer("myTimer", "timerA", "timerB");

        counter.increment();
        counter.increment();
        es.scheduleAtFixedRate(() -> {
            counter.increment();
            timer.record(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }, 1, 3, TimeUnit.SECONDS);

        counter.measure().forEach(v -> log.info("{}", v));
    }

    @Test
    public void getIdTest() {
        MeterRegistry registry = new LoggingMeterRegistry();
        Timer timer = Timer.builder("micrometer").tag("name", "test").register(registry);
        Meter.Id id = timer.getId();
        log.info("{}", id);
    }
}
