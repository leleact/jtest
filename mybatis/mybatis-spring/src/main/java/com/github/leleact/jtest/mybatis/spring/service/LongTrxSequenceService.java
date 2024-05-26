package com.github.leleact.jtest.mybatis.spring.service;

public interface LongTrxSequenceService {

    void syncSequence(String id, int num);
}
