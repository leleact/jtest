package com.github.leleact.jtest.springboot.propertysource;

import com.github.leleact.jtest.springboot.propertysource.configure.AConfig;
import com.github.leleact.jtest.springboot.propertysource.configure.DataSourceConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TestSpringBootPropertysourceApplicationTests {

    @Autowired
    private AConfig aConfig;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Test
    void contextLoads() {
        Assertions.assertEquals("f1", aConfig.getC());
        Assertions.assertEquals("f2", aConfig.getD());
        Assertions.assertEquals("root", dataSourceConfig.getUsername());
    }

}
