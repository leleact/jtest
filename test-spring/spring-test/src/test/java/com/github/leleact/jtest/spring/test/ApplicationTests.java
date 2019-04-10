package com.github.leleact.jtest.spring.test;

import com.github.leleact.jtest.spring.test.dto.Person;
import com.github.leleact.jtest.spring.test.mapper.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTests.class);

    @Resource
    private PersonMapper personMapper;

    @Test
    public void contextLoads() {
        Person person = personMapper.selectByPrimaryKey(3L);
        LOGGER.info("{}", person);
    }
}
