package com.github.leleact.jtest.spring.boot.mybatis.service;

import com.github.leleact.jtest.spring.boot.mybatis.bean.dto.T1;
import com.github.leleact.jtest.spring.boot.mybatis.bean.mapper.T1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BatchInsertService {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Autowired
    private T1Mapper t1Mapper;

    public void batchInsert(Integer record) {

        if (record <= 0) {
            record = 1;
        }
        List<T1> list = new ArrayList<>();
        for (int i = 0; i < record; i++) {
            T1 t1 = new T1();
            t1.setF1(String.format("%08d", atomicInteger.incrementAndGet()));
            list.add(t1);
        }
        t1Mapper.insertBatchSelective(list);
    }

    @PostConstruct
    public void postConstruct() {
        t1Mapper.deleteAll();
    }
}
