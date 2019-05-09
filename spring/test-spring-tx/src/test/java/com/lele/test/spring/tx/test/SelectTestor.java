package com.lele.test.spring.tx.test;

import com.lele.test.spring.tx.bean.dto.T1;
import com.lele.test.spring.tx.bean.mapper.T1Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-datasource.xml"})
public class SelectTestor {

    @Resource
    private T1Mapper t1Mapper;

    @Test
    public void selectMuiltyObjectTest() {

        String f2 = "2";

        T1 t1 = t1Mapper.selectByF2(f2);

        Assert.assertEquals("1", t1.getF1());
    }
}
