package com.github.leleact.jtest.spring.tx;

import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;

@SpringJUnitConfig(locations = {"classpath:spring/spring-context-h2.xml"})
public class TxRecursiveRefTests {
    @Resource
    private T1Mapper t1Mapper;

    @Test
    public void insertTest() {
        T1 t1 = new T1();
        t1.setF1("1");
        t1.setF2("a");
        t1Mapper.insert(t1);
    }
}
