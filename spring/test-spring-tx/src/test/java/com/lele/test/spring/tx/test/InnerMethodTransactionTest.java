package com.lele.test.spring.tx.test;


import com.lele.test.spring.tx.bean.dto.T1;
import com.lele.test.spring.tx.bean.mapper.T1Mapper;
import com.lele.test.spring.tx.service.InnterTransaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-datasource.xml"})
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
        Assert.assertNull(t);
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
        Assert.assertNull(t);
    }
}
