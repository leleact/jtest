package com.lele.test.spring.boot.mybatis;


import com.lele.test.spring.boot.mybatis.bean.dto.T1;
import com.lele.test.spring.boot.mybatis.bean.dto.T2;
import com.lele.test.spring.boot.mybatis.bean.mapper.T1Mapper;
import com.lele.test.spring.boot.mybatis.bean.mapper.T2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        Assert.assertNotNull(t1);
    }

    @Test
    public void getT2() {
        String f1 = "1";
        T2 t2 = t2Mapper.selectByPrimaryKey(f1);
        log.info("{}", t2);
        Assert.assertNull(t2);
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
