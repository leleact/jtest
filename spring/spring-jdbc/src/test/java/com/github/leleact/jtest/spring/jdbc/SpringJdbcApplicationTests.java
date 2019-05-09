package com.github.leleact.jtest.spring.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJdbcApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringJdbcApplicationTests.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void countTest() {
        String sql = "select count(1) from person";
        jdbcTemplate.query(sql, (rs, rowNum)->{
            LOGGER.debug("{}, {}", rs.getInt(1), rowNum);
            return null;
        });

    }

    @Test
    public void queryTest() {
        String sql = "select * from person";
        jdbcTemplate.query(sql, (rs, rowNum) -> {
            LOGGER.debug("{}, {}, {}", rs.getInt("ID"), rs.getString("NAME"), rs.getInt("AGE"));
            return null;
        });
    }
}
