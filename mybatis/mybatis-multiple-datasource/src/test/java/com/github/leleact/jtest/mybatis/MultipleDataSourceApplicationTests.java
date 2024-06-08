package com.github.leleact.jtest.mybatis;

import com.github.leleact.jtest.mybatis.db1.mapper.DbMapper;
import com.github.leleact.jtest.mybatis.db2.mapper.UserMapper;
import jakarta.annotation.Resource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"db1.jdbc-url=jdbc:h2:mem:testdb","db2.jdbc-url=jdbc:h2:mem:testdb","db1.driver-class-name=org.h2.Driver","db2.driver-class-name=org.h2.Driver"})
public class MultipleDataSourceApplicationTests {

    @Resource
    private DbMapper dbMapper1;

    @Resource
    private UserMapper userMapper2;

    @Test
    public void contextLoads() {
    }

    @Disabled
    @Test
    public void multipleDBSelectTest() {
        dbMapper1.selectByPrimaryKey("", "", "");
        userMapper2.selectByPrimaryKey("localhost", "root");
    }
}
