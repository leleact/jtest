package com.github.leleact.jtest.spring.tx.test;

import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import com.github.leleact.jtest.spring.tx.service.S;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;

@SpringJUnitConfig(locations = {"classpath:spring/spring-test-datasource.xml"})
public class RollBackTests {

    @Resource
    private S s;

    @Resource
    private T1Mapper t1Mapper;


    @Test
    public void rollBackForExceptionTest() {
        try {
            T1 t1 = new T1();
            t1.setF1("1");
            t1.setF2("2");
            s.intert(t1);
        } catch (RuntimeException e) {

        }
        T1 t = t1Mapper.selectByPrimaryKey("1");
        Assertions.assertNull(t);
    }

    @Test
    public void rollBackForExceptionTest1() {
        try {
            T1 t1 = new T1();
            t1.setF1("1");
            t1.setF2("2");
            s.intert1(t1);
        } catch (RuntimeException e) {

        }
        T1 t = t1Mapper.selectByPrimaryKey("1");
        Assertions.assertNull(t);
    }

    @Test
    public void rollBackForExceptionTest2() {
        try {
            T1 t1 = new T1();
            t1.setF1("1");
            t1.setF2("2");
            s.intert2(t1);
        } catch (RuntimeException e) {

        }
        T1 t = t1Mapper.selectByPrimaryKey("1");
        Assertions.assertNotNull(t);
        Assertions.assertEquals("不相等", "1", t.getF1());
        Assertions.assertEquals("不相等", "2", t.getF2());
    }
}
