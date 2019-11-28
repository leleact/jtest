package com.lele.test.spring.boot.mybatis;

import com.lele.test.spring.boot.mybatis.bean.dto.T1;
import com.lele.test.spring.boot.mybatis.bean.mapper.T1Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMybatisApplication.class)
public class TestSprintBootMybatisTests {

    private static final Logger log = LoggerFactory.getLogger(TestSprintBootMybatisTests.class);

    @Autowired
    private T1Mapper t1Mapper;

    @Before
    public void clearAllData() {
        int count = t1Mapper.deleteAll();
        log.info("clear {} records", count);
    }

    @Test
    public void myBatisBatchInsertTestCase() {
        AtomicInteger count = new AtomicInteger(0);
        List<T1> t1List = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            T1 t1 = new T1();
            t1.setF1(count.incrementAndGet() + "");
            //t1.setF2(count.incrementAndGet() + "");
            t1List.add(t1);
        }
        int record = t1Mapper.insertBatchSelective(t1List);
        log.debug("insert {} recode", record);
        Assert.assertEquals(10, record);
    }
}
