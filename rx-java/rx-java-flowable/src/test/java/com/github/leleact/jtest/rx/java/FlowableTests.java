package com.github.leleact.jtest.rx.java;

import io.reactivex.rxjava3.core.Flowable;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowableTests {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void helloWorldTest() {
        log.info("Thread Name");
        Flowable.just("Hello world").subscribe(log::info);
    }
}
