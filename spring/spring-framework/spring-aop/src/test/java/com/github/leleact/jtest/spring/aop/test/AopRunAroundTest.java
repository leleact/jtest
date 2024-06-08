package com.github.leleact.jtest.spring.aop.test;

import com.github.leleact.jtest.spring.aop.Executor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.annotation.Resource;

@Slf4j
@SpringJUnitConfig(locations = {"classpath:/test/config/spring/aop/around/spring-around.xml"})
class AopRunAroundTest {

    @Resource
    private Executor executor;

    @Test
    void aopAroundTest() {
        String str = executor.execute1("aa", 1);
        log.debug("str:[" + str + "]");
    }
}
