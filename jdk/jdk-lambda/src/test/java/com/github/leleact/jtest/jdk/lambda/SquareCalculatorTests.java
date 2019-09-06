package com.github.leleact.jtest.jdk.lambda;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SquareCalculatorTests {

    /**
     * SquareCalculator 不能存在两个abstract method.
     */
    @Test
    void interfaceFunctionalTest() {
        SquareCalculator sc = () -> log.info("x");
        sc.a();
    }

}
