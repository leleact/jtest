package com.github.leleact.jtest.spring.framework.ioc.bean.post;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * test.
 *
 * @author leleact
 * @since 2025-06-07
 */
@Slf4j
public class PostBeanProcessorTests {
    @Test
    public void applicationContextLoadTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-beans.xml");
    }
}
