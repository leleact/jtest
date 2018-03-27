package com.lele.test.spring.boot.mybatis.service;

import com.lele.test.spring.boot.mybatis.bean.dto.T1;
import com.lele.test.spring.boot.mybatis.bean.mapper.T1Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TransactionService {

    @Resource
    private T1Mapper t1Mapper;

    @Transactional
    public int insert(T1 t1) {
        return t1Mapper.insertSelective(t1);
    }

    public int insertWrapper(T1 t1) {
        return insert(t1);
    }
}
