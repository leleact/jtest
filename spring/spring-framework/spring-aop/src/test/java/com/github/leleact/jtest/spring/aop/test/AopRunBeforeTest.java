package com.github.leleact.jtest.spring.aop.test;

import com.github.leleact.jtest.spring.aop.Executor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/test/config/spring/aop/around/spring-before.xml"})
class AopRunBeforeTest {

    @Resource
    private Executor executor;

    @Test
    void aopBeforeTest() {
        String str = executor.execute1("aa", 1);
        log.debug("str:[" + str + "]");
    }
}
