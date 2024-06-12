package com.github.leleact.jtest.spring.springframework.test.context.dao.mapper;

import com.github.leleact.jtest.spring.springframework.test.context.dao.entity.T2;
import org.apache.ibatis.annotations.Mapper;

/**
 * T1 Mapper
 *
 * @author leleact
 * @since 2021-01-09
 */
@Mapper
public interface T2Mapper {
    int deleteByPrimaryKey(String f1);

    int insert(T2 record);

    int insertSelective(T2 record);

    T2 selectByPrimaryKey(String f1);

    T2 selectByPrimaryKeyForUpdate(String f1);

    int updateByPrimaryKeySelective(T2 record);

    int updateByPrimaryKey(T2 record);

    T2 selectByF2(String f2);

    int createTable();
}
