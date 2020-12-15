package com.github.leleact.jtest.spring.boot.context;

import com.github.leleact.jtest.spring.boot.context.bean.EchoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PrimaryBeanTests {

    @Resource
    private EchoService echoService;

    @Test
    public void contextLoads() {
        Assertions.assertEquals("mock a", echoService.echo("a"));
    }
}
