package com.github.leleact.jtest.mybatis.db1.mapper;

import com.github.leleact.jtest.mybatis.db1.dto.Db;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DbMapper {
    int deleteByPrimaryKey(@Param("host") String host, @Param("db") String db, @Param("user") String user);

    int insert(Db record);

    int insertSelective(Db record);

    Db selectByPrimaryKey(@Param("host") String host, @Param("db") String db, @Param("user") String user);

    int updateByPrimaryKeySelective(Db record);

    int updateByPrimaryKey(Db record);
}
