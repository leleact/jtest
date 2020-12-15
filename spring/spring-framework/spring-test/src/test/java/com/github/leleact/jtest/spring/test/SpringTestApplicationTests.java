package com.github.leleact.jtest.spring.test;

import com.github.leleact.jtest.spring.test.dto.Person;
import com.github.leleact.jtest.spring.test.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringTestApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringTestApplicationTests.class);

    @Resource
    private PersonMapper personMapper;

    @Test
    void contextLoads() {
        Person person = personMapper.selectByPrimaryKey(3L);
        LOGGER.info("{}", person);
    }
}
