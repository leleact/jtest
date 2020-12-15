package com.github.leleact.jtest.spring.tx.test;

import com.github.leleact.jtest.spring.tx.bean.dto.T2;
import com.github.leleact.jtest.spring.tx.bean.mapper.T2Mapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-datasource.xml"})
public class T2MapperTests {

    private static final Logger log = LoggerFactory.getLogger(T2MapperTests.class);

    @Autowired
    private T2Mapper t2Mapper;

    private final String primaryKey = UUID.randomUUID().toString().replace("-", "");

    @Before
    public void before() {
        T2 t2 = new T2();
        t2.setF1(primaryKey);
        t2.setF2("github");
        t2Mapper.insert(t2);
    }

    @Test
    public void selectTest() {
        Assert.assertNotNull(t2Mapper.selectByPrimaryKey(primaryKey));
    }


    @After
    public void after() {
        t2Mapper.deleteByPrimaryKey(primaryKey);
    }
}
