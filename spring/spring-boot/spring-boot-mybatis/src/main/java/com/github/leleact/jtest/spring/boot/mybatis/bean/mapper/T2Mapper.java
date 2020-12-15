package com.github.leleact.jtest.spring.boot.mybatis.bean.mapper;

import com.github.leleact.jtest.spring.boot.mybatis.bean.dto.T2;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface T2Mapper {

    @Select("SELECT * FROM T2 where f1 = #{arg1}")
    @Results({
            @Result(property = "f01", column = "F1", javaType = java.lang.String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "f02", column = "F2", javaType = java.lang.String.class, jdbcType = JdbcType.VARCHAR)
    })
    T2 selectByPrimaryKey(@Param("arg1") String f1);
}
