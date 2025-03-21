package com.github.leleact.jtest.spring.tx.test;

import jakarta.annotation.Resource;

import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import com.github.leleact.jtest.spring.tx.service.InnerTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;

@SpringJUnitConfig(locations = {"classpath:spring/spring-test-datasource.xml"})
public class InnerMethodTransactionTest {

    @Resource
    private InnerTransaction innerTransaction;

    @Resource
    private T1Mapper t1Mapper;

    @Test
    public void rollBackForExceptionTest() {
        try {
            T1 t1 = new T1();
            t1.setF1("1");
            t1.setF2("2");
            innerTransaction.insert(t1);
        } catch (RuntimeException e) {

        }
        T1 t = t1Mapper.selectByPrimaryKey("1");
        Assertions.assertNull(t);
    }

    @Test
    public void directInvokeTransactionAnnotationMethodTest() {
        String key = UUID.randomUUID().toString().replace("-", "");
        String value = UUID.randomUUID().toString().replace("-", "");
        try {
            T1 t1 = new T1();
            t1.setF1(key);
            t1.setF2(value);
            innerTransaction.insertInner(t1);
        } catch (RuntimeException e) {

        }
        T1 t = t1Mapper.selectByPrimaryKey(key);
        Assertions.assertEquals(value, t.getF2());
    }
}
