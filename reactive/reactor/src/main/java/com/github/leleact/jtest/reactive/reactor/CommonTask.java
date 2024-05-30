package com.github.leleact.jtest.reactive.reactor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonTask {

    public String task1(String input) {
        log.info("task1: " + input);
        return input;
    }

    public Integer task2(String input) {
        log.info("task2: " + input);
        return Integer.parseInt(input);
    }
}
