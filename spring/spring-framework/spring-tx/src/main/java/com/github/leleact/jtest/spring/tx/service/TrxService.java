package com.github.leleact.jtest.spring.tx.service;

import jakarta.annotation.Resource;

import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import com.github.leleact.jtest.spring.tx.bean.mapper.T2Mapper;
import com.github.leleact.jtest.spring.tx.exception.BizException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrxService {

    @Resource
    private T1Mapper t1Mapper;

    @Resource
    private T2Mapper t2Mapper;

    @Transactional
    public void insert(T1 t1) {
        t1Mapper.insert(t1);
        throw new RuntimeException();
    }

    // 应该回滚
    @Transactional(noRollbackFor = BizException.class)
    public void insertWithRollbackException(T1 t1) {
        t1Mapper.insert(t1);
        throw new RuntimeException();
    }

    // 应该不回滚
    @Transactional(noRollbackFor = BizException.class)
    public void insertWithNoRollbackException(T1 t1) {
        t1Mapper.insert(t1);
        throw new BizException();
    }
}
