package com.github.leleact.jtest.reactive.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Slf4j
public class MonoTests {
    @Test
    public void combineTaskTest() {
        CommonTask tasks = new CommonTask();
        Mono<String> m1 = Mono.create((Consumer<MonoSink<String>>) stringMonoSink -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                stringMonoSink.error(new RuntimeException(e));
                return;
            }
            log.info("return mono1");
            stringMonoSink.success(tasks.task1("a"));
        }).log().subscribeOn(Schedulers.boundedElastic());
        Mono<Integer> m2 = Mono.create((Consumer<MonoSink<Integer>>) monoSink -> {
            log.info("return mono2");
            monoSink.success(tasks.task2("10"));
        }).log().subscribeOn(Schedulers.boundedElastic());

        Tuple2<String, Integer> result = Flux.zip(m1, m2)
                                             .log()
                                             .doOnNext(objects -> log.info("T1: {}, T2: {}", objects.getT1(),
                                                 objects.getT2()))
                                             .subscribeOn(Schedulers.boundedElastic())
                                             .blockLast(Duration.of(10000, ChronoUnit.MILLIS));
        Assertions.assertNotNull(result);
        Assertions.assertEquals("a", result.getT1());
        Assertions.assertEquals(10, result.getT2());
    }

    @Test
    public void monoFromFutureTest() {
        CompletableFuture<String> f = new CompletableFuture<>();
        Mono.fromFuture(f).subscribeOn(Schedulers.boundedElastic()).subscribe(s -> {
            log.info("subscribe value: {}", s);
            Assertions.assertEquals("123", s);
        });
        f.complete("123");
    }
}
