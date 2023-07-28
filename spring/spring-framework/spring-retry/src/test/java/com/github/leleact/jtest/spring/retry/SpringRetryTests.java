package com.github.leleact.jtest.spring.retry;

import com.github.leleact.jtest.spring.retry.service.MyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * spring retry test
 *
 * @author leleact
 * @since 2023-07-28
 */
@SpringJUnitConfig(classes = SpringRetryTests.Config.class)
public class SpringRetryTests {
    @ComponentScan(basePackages = "com.github.leleact.jtest.spring.retry")
    public static class Config {
    }

    @Autowired
    private MyService myService;

    @Test
    public void methodRetryTest() {
        int result = myService.invoke(0);
        Assertions.assertEquals(2, result);
    }
}
