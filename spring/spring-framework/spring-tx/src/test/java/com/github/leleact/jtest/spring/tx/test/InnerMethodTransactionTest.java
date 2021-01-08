package com.github.leleact.jtest.spring.tx.test;


import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import com.github.leleact.jtest.spring.tx.service.InnterTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;

@SpringJUnitConfig(locations = {"classpath:spring-test-datasource.xml"})
public class InnerMethodTransactionTest {

    @Resource
    private InnterTransaction innterTransaction;

    @Resource
    private T1Mapper t1Mapper;

    @Test
    public void rollBackForExceptionTest() {
        try {
            T1 t1 = new T1();
            t1.setF1("1");
            t1.setF2("2");
            innterTransaction.intert(t1);
        } catch (RuntimeException e) {

        }
        T1 t = t1Mapper.selectByPrimaryKey("1");
        Assertions.assertNull(t);
    }


    @Test
    public void directInvokeTransactionAnnotationMethodTest() {
        try {
            T1 t1 = new T1();
            t1.setF1("1");
            t1.setF2("2");
            innterTransaction.intertInnter(t1);
        } catch (RuntimeException e) {

        }
        T1 t = t1Mapper.selectByPrimaryKey("1");
        Assertions.assertNull(t);
    }
}
