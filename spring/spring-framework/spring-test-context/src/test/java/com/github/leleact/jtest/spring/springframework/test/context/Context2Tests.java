package com.github.leleact.jtest.spring.springframework.test.context;

import com.github.leleact.jtest.spring.springframework.test.context.dao.entity.T1;
import com.github.leleact.jtest.spring.springframework.test.context.dao.mapper.T1Mapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@DirtiesContext
@ContextConfiguration(locations = "classpath:META-INF/spring/spring-context1.xml")
public class Context2Tests extends BaseContextTests {
    @Autowired
    private T1Mapper t1Mapper;

    @BeforeAll
    public static void beforeTest() {
        MDC.put("TEST_CLASS", "Context2Tests");
    }

    @Test
    public void emptyTest() {
        T1 t1 = t1Mapper.selectByPrimaryKey("22");
        Assertions.assertNotNull(t1);
        Assertions.assertEquals("aa", t1.getF2());
    }

    @AfterAll
    public static void afterTest() {
        MDC.remove("TEST_CLASS");
    }
}
