package com.github.leleact.jtest.spring.tx.service;

import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.mapper.T1Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * recursive tx ref
 *
 * @author leleact
 * @since 2021-01-08
 */
@Service
public class TxRecursiveRefService {
    @Resource
    private TxRecursiveRefService txRecursiveRefService;

    @Resource
    private T1Mapper t1Mapper;

    public void innerRefExecuteTx() {
        // have transaction
        txRecursiveRefService.saveT1Data();
    }

    public void innerExecuteTx() {
        // no transaction
        saveT1Data();
    }

    @Transactional
    public void saveT1Data() {
        T1 t1 = new T1();
        t1.setF1("1");
        t1.setF2("a");
        t1Mapper.insert(t1);
    }

}
