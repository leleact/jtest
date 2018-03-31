package com.lele.test.spring.ioc.lifecycle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(value = {"classpath:spring/lifecycle/spring-bean-lifecycle.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class LifeCycleBeanTests {

    private static final Logger log = LoggerFactory.getLogger(LifeCycleBeanTests.class);

    @Test
    public void initSpringContextTest() {
        log.info("initialize spring context success");
    }

}
