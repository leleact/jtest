package com.github.leleact.jtest.mybatis.spring.db.mapper;

import com.github.leleact.jtest.mybatis.spring.db.entity.T1;
import org.apache.ibatis.annotations.Mapper;

/**
 * T1 Mapper
 *
 * @author leleact
 * @since 2021-01-09
 */
@Mapper
public interface T1Mapper {
    int deleteByPrimaryKey(String f1);

    int insert(T1 record);

    int insertSelective(T1 record);

    T1 selectByPrimaryKey(String f1);

    int updateByPrimaryKeySelective(T1 record);

    int updateByPrimaryKey(T1 record);

    T1 selectByF2(String f2);
}
