package com.github.leleact.jtest.reactive.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import reactor.core.scheduler.Schedulers;

import java.util.function.Consumer;

@Slf4j
public class MonoTests {
    @Test
    public void combineTaskTest() {
        CommonTask tasks = new CommonTask();
        Mono<String> m1 = Mono.create(new Consumer<MonoSink<String>>() {
            @Override
            public void accept(MonoSink<String> stringMonoSink) {
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("return mono1");
                stringMonoSink.success(tasks.task1("a"));
            }
        }).subscribeOn(Schedulers.boundedElastic());
        //Mono<String> m1 = Mono.just(tasks.task1("a")).subscribeOn(Schedulers.boundedElastic());
        Mono<Integer> m2 = Mono.create(new Consumer<MonoSink<Integer>>() {
            @Override
            public void accept(MonoSink<Integer> monoSink) {
                log.info("return mono2");
                monoSink.success(tasks.task2("10"));
            }
        }).subscribeOn(Schedulers.boundedElastic());
        //Mono<Integer> m2 = Mono.just(tasks.task2("10")).subscribeOn(Schedulers.boundedElastic());
        //        Flux<Tuple2<String, Integer>> flux = Flux.zip(m1, m2).subscribeOn(Schedulers.boundedElastic());
        //        flux.subscribe(new Consumer<Tuple2<String, Integer>>() {
        //            @Override
        //            public void accept(Tuple2<String, Integer> objects) {
        //                log.info("T1: {}, T2: {}", objects.getT1(), objects.getT2());
        //            }
        //        });
        //        flux.blockLast();

        try {
            Thread.sleep(60000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
