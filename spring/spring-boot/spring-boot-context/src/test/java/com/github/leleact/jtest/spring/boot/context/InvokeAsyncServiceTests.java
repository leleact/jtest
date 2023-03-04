package com.github.leleact.jtest.spring.boot.context;

import com.github.leleact.jtest.spring.boot.context.service.InvokeAsyncService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvokeAsyncServiceTests {

    @Resource
    private InvokeAsyncService invokeAsyncService;

    @Test
    public void echoTest() {
        Assertions.assertEquals("hello world", invokeAsyncService.echo("world"));
    }
}
