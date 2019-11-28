package com.lele.test.spring.boot.mybatis;


import com.lele.test.spring.boot.mybatis.bean.dto.T1;
import com.lele.test.spring.boot.mybatis.bean.mapper.T1Mapper;
import com.lele.test.spring.boot.mybatis.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMybatisApplication.class)
public class SpringBootTransactionTest {

    private static final Logger log = LoggerFactory.getLogger(SpringBootTransactionTest.class);

    @Autowired
    private TransactionService transactionService;

    @Resource
    private T1Mapper t1Mapper;

    @Before
    public void before() {
        long count = t1Mapper.deleteAll();
        log.info("delete {} counts", count);
    }

    @Test
    public void insertTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());
        T1 t1 = new T1();
        t1.setF1("1");
        t1.setF2(timestamp);
        int code = transactionService.insert(t1);
        log.info("{}", code);
    }

    @Test
    public void insertWrapTest() {
        log.debug("start insertwrap test...");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());
        T1 t1 = new T1();
        t1.setF1("1");
        t1.setF2(timestamp);
        int code = transactionService.insertWrapper(t1);
        log.info("{}", code);
    }
}
