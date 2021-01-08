package com.github.leleact.jtest.spring.tx.test;

import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import com.github.leleact.jtest.spring.tx.util.TransactionCall;
import com.github.leleact.jtest.spring.tx.util.TransactionUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;


@SpringJUnitConfig(locations = {"classpath:spring/spring-test-datasource.xml"})
@Configuration
public class LambdaTransactionTests {

    private static final Logger logger = LoggerFactory.getLogger(LambdaTransactionTests.class);

    @Resource
    private TransactionUtil transactionUtil;

    @Resource
    private T1Mapper t1Mapper;

    @Test
    public void transactionTest() {
        transactionUtil.transaction(() -> {
            logger.debug("====================");
            t1Mapper.selectByPrimaryKey("1");
            return null;
        });
    }

    @Test
    public void transactionTest1() {
        transactionUtil.transaction(new TransactionCall<Void>() {
            @Override
            public Void call() {
                logger.debug("====================");
                t1Mapper.selectByPrimaryKey("1");
                return null;
            }
        });
    }
}
