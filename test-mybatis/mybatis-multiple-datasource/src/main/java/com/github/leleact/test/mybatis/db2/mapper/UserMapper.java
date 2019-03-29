package com.github.leleact.test.mybatis.db2.mapper;

import com.github.leleact.test.mybatis.db2.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(@Param("host") String host, @Param("user") String user);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("host") String host, @Param("user") String user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);
}