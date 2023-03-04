package com.github.leleact.jtest.spring.boot.context;

import com.github.leleact.jtest.spring.boot.context.bean.EchoService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PrimaryBeanTests {

    @Resource
    private EchoService echoService;

    @Test
    public void contextLoads() {
        Assertions.assertEquals("mock a", echoService.echo("a"));
    }
}
