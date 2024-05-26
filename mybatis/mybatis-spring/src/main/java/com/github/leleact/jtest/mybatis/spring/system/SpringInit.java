package com.github.leleact.jtest.mybatis.spring.system;

import com.github.leleact.jtest.mybatis.spring.db.entity.T1;
import com.github.leleact.jtest.mybatis.spring.db.entity.T2;
import com.github.leleact.jtest.mybatis.spring.db.mapper.T1Mapper;
import com.github.leleact.jtest.mybatis.spring.db.mapper.T2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Objects;

public class SpringInit implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private T1Mapper t1Mapper;

    @Autowired
    private T2Mapper t2Mapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        t1Mapper.createTable();
        t2Mapper.createTable();

        T1 t1 = t1Mapper.selectByPrimaryKey("a");
        if (Objects.isNull(t1)) {
            t1 = new T1();
            t1.setF1("a");
            t1.setF2("0");
            t1Mapper.insert(t1);
        }

        T2 t2 = t2Mapper.selectByPrimaryKey("a");
        if (Objects.isNull(t2)) {
            t2 = new T2();
            t2.setF1("a");
            t2.setF2("0");
            t2Mapper.insert(t2);
        }
    }
}
