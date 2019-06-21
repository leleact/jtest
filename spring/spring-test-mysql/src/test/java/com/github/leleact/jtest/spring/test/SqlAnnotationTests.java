package com.github.leleact.jtest.spring.test;

import com.github.leleact.jtest.spring.test.mapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;

@SpringBootTest
@DirtiesContext
@Slf4j
class SqlAnnotationTests {

    @Resource
    PersonMapper personMapper;

    @Test
    @Sql(scripts = {"classpath:test-del.sql", "classpath:test-init.sql"})
    @Sql(scripts = {"classpath:test-init.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void test1() throws InterruptedException {

        Thread.sleep(1000L);

        personMapper.selectAll().forEach(x-> log.info("{}", x));
    }

    @Test
    @Sql(scripts = {"classpath:test-del.sql", "classpath:test-init.sql"})
    @Sql(scripts = {"classpath:test-init.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void test2() throws InterruptedException {

        Thread.sleep(1000L);

        personMapper.selectAll().forEach(x-> log.info("{}", x));
    }
}
