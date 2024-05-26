package com.github.leleact.jtest.mybatis.spring.service;

import com.github.leleact.jtest.mybatis.spring.db.entity.T1;
import com.github.leleact.jtest.mybatis.spring.db.entity.T2;
import com.github.leleact.jtest.mybatis.spring.db.mapper.T1Mapper;
import com.github.leleact.jtest.mybatis.spring.db.mapper.T2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LongTrxSequenceServiceImpl implements LongTrxSequenceService {
    @Autowired
    private T1Mapper t1Mapper;

    @Autowired
    private T2Mapper t2Mapper;

    @Transactional
    @Override
    public void syncSequence(String id, int num) {
        T1 t1 = t1Mapper.selectByPrimaryKeyForUpdate(id);
        if (Objects.isNull(t1)) {
            log.error("id: {} not found", id);
            return;
        }

        T2 t2 = t2Mapper.selectByPrimaryKeyForUpdate(id);
        if (Objects.isNull(t2)) {
            log.error("id: {} not found", id);
            return;
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            log.error("", e);
            throw new RuntimeException(e);
        }

        int currT1 = Integer.parseInt(t1.getF2());
        int currT2 = Integer.parseInt(t2.getF2());
        if (currT1 != currT2) {
            log.error("t1: {} != t2: {}", t1.getF2(), t2.getF2());
            throw new RuntimeException("compare fail");
        }

        currT1 += num;
        currT2 += num;
        t1.setF2("" + currT1);
        t2.setF2("" + currT2);
        t1Mapper.updateByPrimaryKey(t1);
        t2Mapper.updateByPrimaryKey(t2);
    }
}
