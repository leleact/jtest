package com.github.leleact.jtest.mybatis;

import com.github.leleact.jtest.mybatis.db1.mapper.DbMapper;
import com.github.leleact.jtest.mybatis.db2.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
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
