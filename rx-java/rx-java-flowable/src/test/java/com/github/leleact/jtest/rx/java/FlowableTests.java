package com.github.leleact.jtest.rx.java;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FlowableTests {

    @Test
    public void helloWorldTest() {
        log.info("Thread Name");
        Flowable.just("Hello world").subscribe(log::info);
    }

    @Test
    public void flatMapTest() {
        Flowable.just(1, 2).flatMap(it -> Flowable.just(it).subscribeOn(Schedulers.computation())//控制在哪些线程上生成sub-stream
                                                  .map(i -> {
                                                      System.out.println(i + "  thread: " + Thread.currentThread());
                                                      return i;
                                                  })).subscribe(it -> {
            System.out.println("onNext:" + it + "  thread: " + Thread.currentThread());
        });
    }

    @Test
    public void parallelTest() {
        Flowable.just(1, 2).parallel().runOn(Schedulers.io())//指定在哪些线程上并发执行
                .map(it -> {
                    System.out.println(it + "  thread: " + Thread.currentThread());
                    return it;
                }).sequential().subscribe(it -> {
                    System.out.println("onNext:" + it + "  thread: " + Thread.currentThread());
                });
    }
}
