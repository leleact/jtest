package com.github.leleact.jtest.mybatis.spring;

import com.github.leleact.jtest.mybatis.spring.db.entity.T1;
import com.github.leleact.jtest.mybatis.spring.db.mapper.T1Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * mapper tests
 *
 * @author leleact
 * @since 2021-01-09
 */
@SpringJUnitConfig(locations = {"classpath*:META-INF/spring/spring-context-h2.xml"})
class MapperTests {
    @Resource
    private T1Mapper t1Mapper;

    @Test
    void selectTest() {
        T1 t1 = t1Mapper.selectByPrimaryKey("11");
        Assertions.assertNotNull(t1);
    }

    @Test
    public void batchUpdateTest() {
        List<T1> list = new ArrayList<>();
        {
            T1 t1 = new T1();
            t1.setF1(UUID.randomUUID().toString().replaceAll("-", ""));
            t1Mapper.insertSelective(t1);
            list.add(t1);
        }
        {
            T1 t1 = new T1();
            t1.setF1(UUID.randomUUID().toString().replaceAll("-", ""));
            t1Mapper.insertSelective(t1);
            list.add(t1);
        }
        {
            T1 t1 = new T1();
            t1.setF1(UUID.randomUUID().toString().replaceAll("-", ""));
            t1Mapper.insertSelective(t1);
            list.add(t1);
        }
        list.forEach(i -> i.setF1(UUID.randomUUID().toString().replaceAll("-", "")));
        t1Mapper.batchUpdate(list);
    }
}
