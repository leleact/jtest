package com.lele.test.database.mysql.bean.mapper;

import com.lele.test.database.mysql.bean.dto.T2;

public interface T2Mapper {
    int deleteByPrimaryKey(String f1);

    int insert(T2 record);

    int insertSelective(T2 record);

    T2 selectByPrimaryKey(String f1);

    int updateByPrimaryKeySelective(T2 record);

    int updateByPrimaryKey(T2 record);

    int deleteAll();
}