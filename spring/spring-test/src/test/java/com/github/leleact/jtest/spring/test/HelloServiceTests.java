package com.github.leleact.jtest.spring.test;

import com.github.leleact.jtest.spring.test.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * hello service tests.
 *
 * @author leleact
 * @since 1.0
 */
@SpringBootTest
class HelloServiceTests {

    @Autowired
    HelloService helloService;

    @Test
    void helloTest() {
        Assertions.assertEquals("real hello xxx", helloService.hello("xxx"));
    }
}
