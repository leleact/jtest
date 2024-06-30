package com.github.leleact.jtest.reactive.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class FluxTests {
    @Test
    public void parallelFluxTest() {
        //@formatter:off
        Flux.range(1, 2000)
            .buffer(4, 2)
            .log().parallel(2)
            .runOn(Schedulers.parallel())
            .subscribe(s -> log.info("show string: {}", s));
        //@formatter:on
    }
}
