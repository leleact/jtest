package com.github.leleact.jtest.spring.tx.service;

import jakarta.annotation.Resource;

import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InnerTransaction {

    @Resource
    private T1Mapper t1Mapper;

    //@Transactional
    public void insertInner(T1 t1) {
        t1Mapper.insert(t1);
        throw new RuntimeException();
    }

    @Transactional
    public void insert(T1 t1) {
        insertInner(t1);
    }
}
