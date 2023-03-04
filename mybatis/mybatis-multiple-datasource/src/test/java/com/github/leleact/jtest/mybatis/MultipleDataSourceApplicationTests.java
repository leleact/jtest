package com.github.leleact.jtest.mybatis;

import com.github.leleact.jtest.mybatis.db1.mapper.DbMapper;
import com.github.leleact.jtest.mybatis.db2.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MultipleDataSourceApplicationTests {

    @Resource
    private DbMapper dbMapper1;

    @Resource
    private UserMapper userMapper2;

    @Test
    public void contextLoads() {
    }

    @Test
    public void multipleDBSelectTest() {
        dbMapper1.selectByPrimaryKey("", "", "");
        userMapper2.selectByPrimaryKey("localhost", "root");
    }
}
