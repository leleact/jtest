package com.github.leleact.jtest.spring.boot.context;

import com.github.leleact.jtest.spring.boot.context.service.InvokeAsyncService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class InvokeAsyncServiceTests {

    @Resource
    private InvokeAsyncService invokeAsyncService;

    @Test
    public void echoTest() {
        Assertions.assertEquals("hello world", invokeAsyncService.echo("world"));
    }
}
