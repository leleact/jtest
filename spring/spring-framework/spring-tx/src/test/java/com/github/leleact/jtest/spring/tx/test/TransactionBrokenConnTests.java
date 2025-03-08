package com.github.leleact.jtest.spring.tx.test;

import jakarta.annotation.Resource;

import com.github.leleact.jtest.spring.tx.service.OuterTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.UnexpectedRollbackException;

/**
 * transaction broken connection tests.
 *
 * @author leleact
 * @since 2025-03-08
 */
@SpringJUnitConfig(locations = {"classpath:spring/spring-test-datasource.xml"})
public class TransactionBrokenConnTests {
    @Resource
    private OuterTransaction outerTransaction;

    @Test
    public void brokenConnectionTest() {
        Assertions.assertThrows(UnexpectedRollbackException.class, () -> {
            outerTransaction.outerInsert();
        });
    }
}
