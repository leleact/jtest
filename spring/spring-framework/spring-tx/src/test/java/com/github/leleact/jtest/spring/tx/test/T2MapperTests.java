package com.github.leleact.jtest.spring.tx.test;

import com.github.leleact.jtest.spring.tx.bean.dto.T2;
import com.github.leleact.jtest.spring.tx.bean.mapper.T2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;

@Slf4j
@SpringJUnitConfig(locations = {"classpath:spring/spring-test-datasource.xml"})
public class T2MapperTests {
    private static final String primaryKey = UUID.randomUUID().toString().replace("-", "");

    @Autowired
    private T2Mapper t2Mapper;

    public void before() {
        log.info("before test");
        T2 t2 = new T2();
        t2.setF1(primaryKey);
        t2.setF2("github");
        t2Mapper.insert(t2);
    }

    public void after() {
        t2Mapper.deleteByPrimaryKey(primaryKey);
    }

    @Test
    public void selectTest() {
        before();
        Assertions.assertNotNull(t2Mapper.selectByPrimaryKey(primaryKey));
        after();
    }
}
