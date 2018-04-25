package com.lele.test.database.mysql.test;

import com.lele.test.database.mysql.bean.dto.T1;
import com.lele.test.database.mysql.bean.mapper.T1Mapper;
import com.lele.test.database.mysql.bean.mapper.T2Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestDataBaseMysqlUpdateLockTests {

    private static final Logger log = LoggerFactory.getLogger(TestDataBaseMysqlUpdateLockTests.class);

    @Autowired
    private T1Mapper t1Mapper;

    @Autowired
    private T2Mapper t2Mapper;

    private T1 t1 = new T1();

    @Before
    public void insertData() {

        t1Mapper.deleteAll();
        t2Mapper.deleteAll();

        t1.setF1(UUID.randomUUID().toString().replace("-",""));
        t1.setF2("1");
        t1Mapper.insert(t1);
    }

    @Test
    @Transactional
    public void updateLock() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            T1 tt = t1Mapper.selectByPrimaryKeyLock(t1.getF1());
            tt.setF2("2");
            int code = t1Mapper.updateByPrimaryKeySelective(tt);
            Assert.assertEquals(1, code);
        }
        log.info("elapsed time: {}", System.currentTimeMillis() - start);
    }
}
