package com.github.leleact.jtest.spring.tx;

import com.github.leleact.jtest.spring.tx.service.TxRecursiveRefService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.annotation.Resource;

@SpringJUnitConfig(locations = {"classpath:spring/spring-context-h2.xml"})
class TxRecursiveRefTests {
    @Resource
    private TxRecursiveRefService txRecursiveRefService;

    /**
     * innerExecuteTx have a inner aop transaction, recursive ref txRecursiveRefService make inner aop effect
     */
    @Test
    void recursiveAopTest() {
        Assertions.assertDoesNotThrow(() -> {
            txRecursiveRefService.innerRefExecuteTx();
        });
    }

    @Test
    void innerAopTest() {
        Assertions.assertDoesNotThrow(() -> {
            txRecursiveRefService.innerExecuteTx();
        });
    }

    @Test
    void txAopTest() {
        Assertions.assertDoesNotThrow(() -> {
            txRecursiveRefService.saveT1Data();
        });
    }
}
