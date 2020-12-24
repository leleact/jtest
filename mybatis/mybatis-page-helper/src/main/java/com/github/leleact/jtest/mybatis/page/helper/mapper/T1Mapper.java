package com.github.leleact.jtest.mybatis.page.helper.mapper;

import com.github.leleact.jtest.mybatis.page.helper.entity.T1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface T1Mapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(T1 record);

    int insertSelective(T1 record);

    T1 selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(T1 record);

    int updateByPrimaryKey(T1 record);

    List<T1> selectByBirthday(@Param("birthday") String birthday);
}
