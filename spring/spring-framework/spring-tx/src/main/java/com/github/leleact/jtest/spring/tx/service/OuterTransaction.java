package com.github.leleact.jtest.spring.tx.service;

import jakarta.annotation.Resource;
import java.util.UUID;

import com.github.leleact.jtest.spring.tx.bean.dto.T1;
import com.github.leleact.jtest.spring.tx.bean.dto.T2;
import com.github.leleact.jtest.spring.tx.bean.mapper.T2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * outer transaction.
 *
 * @author leleact
 * @since 2025-03-08
 */
@Slf4j
@Service
public class OuterTransaction {
    @Resource
    private InnerTransaction innerTransaction;

    @Resource
    private T2Mapper t2Mapper;

    @Transactional
    public void outerInsert() {
        T1 t1 = new T1();
        t1.setF1(UUID.randomUUID().toString().replace("-", ""));
        t1.setF2(UUID.randomUUID().toString().replace("-", ""));
        try {
            // This will break the connection
            innerTransaction.insert(t1);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        }

        T2 t2 = new T2();
        t2.setF1(UUID.randomUUID().toString().replace("-", ""));
        t2.setF2(UUID.randomUUID().toString().replace("-", ""));
        t2Mapper.insert(t2);
        // commit will fail because of broken transaction connection
    }
}
