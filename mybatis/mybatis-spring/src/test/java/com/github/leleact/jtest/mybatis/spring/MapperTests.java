package com.github.leleact.jtest.mybatis.spring;

import com.github.leleact.jtest.mybatis.spring.db.entity.T1;
import com.github.leleact.jtest.mybatis.spring.db.mapper.T1Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;

/**
 * mapper tests
 *
 * @author leleact
 * @since 2021-01-09
 */
@SpringJUnitConfig(locations = {"classpath*:spring/spring-context-h2.xml"})
class MapperTests {
    @Resource
    private T1Mapper t1Mapper;

    @Test
    void selectTest() {
        T1 t1 = t1Mapper.selectByPrimaryKey("11");
        Assertions.assertNotNull(t1);
    }
}
