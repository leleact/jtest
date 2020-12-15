package com.github.leleact.jtest.spring.ioc.lifecycle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(value = {"classpath:spring/lifecycle/spring-bean-lifecycle.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class LifeCycleBeanTests {

    private static final Logger log = LoggerFactory.getLogger(LifeCycleBeanTests.class);

    @Test
    void initSpringContextTest() {
        log.info("initialize spring context success");
    }

}
