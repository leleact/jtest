package com.github.leleact.jtest.spring.tx.service;

import com.github.leleact.jtest.spring.tx.exception.BizException;
import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import com.github.leleact.jtest.spring.tx.bean.mapper.T2Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

@Service
public class S {

    @Resource
    private T1Mapper t1Mapper;

    @Resource
    private T2Mapper t2Mapper;

    @Transactional
    public void intert(T1 t1) {
        t1Mapper.insert(t1);
        throw new RuntimeException();
    }

    // 应该回滚
    @Transactional(noRollbackFor = BizException.class)
    public void intert1(T1 t1) {
        t1Mapper.insert(t1);
        throw new RuntimeException();
    }

    // 应该不回滚
    @Transactional(noRollbackFor = BizException.class)
    public void intert2(T1 t1) {
        t1Mapper.insert(t1);
        throw new BizException();
    }


}
