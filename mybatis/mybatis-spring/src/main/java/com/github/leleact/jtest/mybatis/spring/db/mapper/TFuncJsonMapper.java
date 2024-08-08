package com.github.leleact.jtest.mybatis.spring.db.mapper;

import com.github.leleact.jtest.mybatis.spring.db.entity.TFuncJson;
import org.apache.ibatis.annotations.Mapper;

/**
 * T1 Mapper
 *
 * @author leleact
 * @since 2021-01-09
 */
@Mapper
public interface TFuncJsonMapper {
    int deleteByPrimaryKey(Long tid);

    int insert(TFuncJson record);

    int insertSelective(TFuncJson record);

    TFuncJson selectByPrimaryKey(Long tid);

    TFuncJson selectByPrimaryKeyForUpdate(Long tid);

    int updateByPrimaryKeySelective(TFuncJson record);

    int updateByPrimaryKey(TFuncJson record);
}
