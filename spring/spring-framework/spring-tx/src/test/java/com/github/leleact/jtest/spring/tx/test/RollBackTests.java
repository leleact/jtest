package com.github.leleact.jtest.spring.tx.test;

import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import com.github.leleact.jtest.spring.tx.service.TrxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.annotation.Resource;
import java.util.UUID;

@SpringJUnitConfig(locations = {"classpath:spring/spring-test-datasource.xml"})
public class RollBackTests {

    @Resource
    private TrxService trxService;

    @Resource
    private T1Mapper t1Mapper;

    @Test
    public void rollBackForExceptionTest() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            T1 t1 = new T1();
            t1.setF1(id);
            t1.setF2("2");
            trxService.insert(t1);
        } catch (RuntimeException e) {

        }
        T1 t = t1Mapper.selectByPrimaryKey(id);
        Assertions.assertNull(t);
    }

    @Test
    public void rollBackForExceptionTest1() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            T1 t1 = new T1();
            t1.setF1(id);
            t1.setF2("2");
            trxService.insertWithRollbackException(t1);
        } catch (RuntimeException e) {

        }
        T1 t = t1Mapper.selectByPrimaryKey(id);
        Assertions.assertNull(t);
    }

    @Test
    public void rollBackForExceptionTest2() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            T1 t1 = new T1();
            t1.setF1(id);
            t1.setF2("2");
            trxService.insertWithNoRollbackException(t1);
        } catch (RuntimeException e) {

        }
        T1 t = t1Mapper.selectByPrimaryKey(id);
        Assertions.assertNotNull(t);
        Assertions.assertEquals(id, t.getF1(), "不相等");
        Assertions.assertEquals("2", t.getF2(), "不相等");
    }
}
