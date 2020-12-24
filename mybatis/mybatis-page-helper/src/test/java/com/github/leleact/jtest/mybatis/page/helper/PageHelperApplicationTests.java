package com.github.leleact.jtest.mybatis.page.helper;

import com.github.leleact.jtest.mybatis.page.helper.entity.T1;
import com.github.leleact.jtest.mybatis.page.helper.mapper.T1Mapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@SpringBootTest
class PageHelperApplicationTests {
    @Resource
    private T1Mapper t1Mapper;

    @Test
    @Sql(scripts = "classpath:/sql/create_table.sql")
    void pageSelectTest() {
        {
            T1 t1 = new T1();
            t1.setName("a");
            t1.setAge(12);
            t1.setBirthday("20201201");
            t1.setCreateTime(new Date());
            t1Mapper.insertSelective(t1);
        }
        {
            Page<T1> t1Page = PageMethod.startPage(0, 12).doSelectPage(() -> t1Mapper.selectByBirthday("20201201"));
            Assertions.assertEquals(1, t1Page.getTotal());
        }
    }
}
