package com.github.leleact.jtest.spring.tx.test;

import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;

@SpringJUnitConfig(locations = {"classpath:spring/spring-test-datasource.xml"})
public class SelectTestor {

    @Resource
    private T1Mapper t1Mapper;

    @Test
    public void selectMuiltyObjectTest() {
        String f2 = "2";
        T1 t1 = t1Mapper.selectByF2(f2);
        Assertions.assertEquals("1", t1.getF1());
    }
}
