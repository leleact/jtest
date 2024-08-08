package com.github.leleact.jtest.mybatis.spring.application;

import com.github.leleact.jtest.mybatis.spring.db.entity.JsonPojo;
import com.github.leleact.jtest.mybatis.spring.db.entity.TFuncJson;
import com.github.leleact.jtest.mybatis.spring.db.mapper.TFuncJsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * MySQL json Type application.
 *
 * @author leleact
 * @since 2024-08-06
 */
@Slf4j
public class MySQLJsonTypeApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-long-trx.xml");
        TFuncJsonMapper mapper = context.getBean(TFuncJsonMapper.class);
        TFuncJson pojo = new TFuncJson();
        pojo.setTid(1L);
        List<JsonPojo> list = new ArrayList<>();
        {
            JsonPojo pojo1 = new JsonPojo();
            pojo1.setId("a");
            pojo1.setValue("b");
            list.add(pojo1);
        }
        pojo.setData(list);
        pojo.setCreateTime(new Date());
        pojo.setLastUpdateTime(new Date());
        mapper.insert(pojo);
    }
}
