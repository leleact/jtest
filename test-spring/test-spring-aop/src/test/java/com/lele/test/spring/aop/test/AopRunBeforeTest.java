package com.lele.test.spring.aop.test;

import com.lele.test.spring.aop.Excutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/config/spring/aop/around/spring-before.xml"})
public class AopRunBeforeTest {

    private static Logger log = LoggerFactory.getLogger(RunWith.class);

    @Resource
    private Excutor excutor;

    @Test
    public void aopBeforeTest() {
        String str = excutor.excute1("aa", 1);
        log.debug("str:[" + str + "]");
    }
}