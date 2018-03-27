package com.lele.test.spring.tx.service;

import com.lele.test.spring.tx.bean.dto.T1;
import com.lele.test.spring.tx.bean.mapper.T1Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class InnterTransaction {

    @Resource
    private T1Mapper t1Mapper;

    //@Transactional
    public void intertInnter(T1 t1) {
        t1Mapper.insert(t1);
        throw new RuntimeException();
    }


    public void intert(T1 t1) {
        intertInnter(t1);
    }

}
