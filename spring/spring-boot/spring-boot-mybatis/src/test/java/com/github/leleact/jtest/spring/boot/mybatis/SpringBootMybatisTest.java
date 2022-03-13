package com.github.leleact.jtest.spring.boot.mybatis;


import com.github.leleact.jtest.spring.boot.mybatis.bean.dto.T1;
import com.github.leleact.jtest.spring.boot.mybatis.bean.dto.T2;
import com.github.leleact.jtest.spring.boot.mybatis.bean.mapper.T1Mapper;
import com.github.leleact.jtest.spring.boot.mybatis.bean.mapper.T2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
public class SpringBootMybatisTest {

    @Resource
    private T1Mapper t1Mapper;

    @Resource
    private T2Mapper t2Mapper;

    @Test
    public void getT1() {
        String f1 = "1";
        T1 t1 = t1Mapper.selectByPrimaryKey(f1);
        log.info("{}", t1);
        Assertions.assertNotNull(t1);
    }

    @Test
    public void getT2() {
        String f1 = "1";
        T2 t2 = t2Mapper.selectByPrimaryKey(f1);
        log.info("{}", t2);
        Assertions.assertNull(t2);
    }

    @Test
    public void selectiveTest() {
        {
            T1 t1 = new T1();
            T1 t11 = t1Mapper.selectBySelective(t1);
            log.info("{}", t11);
        }
        {
            T1 t1 = new T1();
            t1.setF1("1");
            T1 t11 = t1Mapper.selectBySelective(t1);
            log.info("{}", t11);
        }

        {
            T1 t1 = new T1();
            t1.setF2("a");
            T1 t11 = t1Mapper.selectBySelective(t1);
            log.info("{}", t11);
        }
        {
            T1 t1 = new T1();
            t1.setF1("1");
            t1.setF2("a");
            T1 t11 = t1Mapper.selectBySelective(t1);
            log.info("{}", t11);
        }
    }

}
