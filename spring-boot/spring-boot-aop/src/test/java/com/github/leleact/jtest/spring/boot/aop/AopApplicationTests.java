package com.github.leleact.jtest.spring.boot.aop;

import com.github.leleact.jtest.spring.boot.aop.service.AopService;
import com.github.leleact.jtest.spring.boot.aop.service.ISupperMethod;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
public class AopApplicationTests {

    @Resource
    private AopService aopService;

    @Resource
    private ISupperMethod iSupperMethod;

    @Test
    public void aopMethodTest() {
        String res = aopService.execute("world");
        Assertions.assertEquals("hello, world", res);
    }

    @Test
    public void supperMethodTest() {
        String res = iSupperMethod.echo("world");
        Assertions.assertEquals("hello, world", res);
    }
}
