package com.github.leleact.jtest.spring.aop.test;

import com.github.leleact.jtest.spring.aop.Executor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.annotation.Resource;

@SpringJUnitConfig(locations = {"classpath:/test/config/spring/aop/around/spring-around-no-args.xml"})
class AopRoundWithNoArgsTest {

    @Resource
    private Executor executor;

    @Test
    void executor2Test() {
        executor.execute2("嘿嘿");
    }
}
