package com.github.leleact.jtest.spring.aop.test;


import com.github.leleact.jtest.spring.aop.Executor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@Slf4j
@ContextConfiguration(locations = {"classpath:/test/config/spring/aop/around/spring-around-no-args.xml"})
class AopRoundWithNoArgsTest {

    @Resource
    private Executor executor;

    @Test
    void executor2Test() {
        executor.execute2("嘿嘿");
    }

}
