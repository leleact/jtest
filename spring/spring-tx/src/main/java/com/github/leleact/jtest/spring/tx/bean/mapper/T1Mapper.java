package com.github.leleact.jtest.spring.tx.bean.mapper;

import com.github.leleact.jtest.spring.tx.bean.dto.T1;

public interface T1Mapper {
    int deleteByPrimaryKey(String f1);

    int insert(T1 record);

    int insertSelective(T1 record);

    T1 selectByPrimaryKey(String f1);

    int updateByPrimaryKeySelective(T1 record);

    int updateByPrimaryKey(T1 record);

    T1 selectByF2(String f2);
}
