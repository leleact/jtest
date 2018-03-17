package com.lele.test.mybatis.generator.bean.mapper;

import com.lele.test.mybatis.generator.bean.dto.QrsRouteInfo;

public interface QrsRouteInfoMapper {
    int deleteByPrimaryKey(String routeId);

    int insert(QrsRouteInfo record);

    int insertSelective(QrsRouteInfo record);

    QrsRouteInfo selectByPrimaryKey(String routeId);

    int updateByPrimaryKeySelective(QrsRouteInfo record);

    int updateByPrimaryKey(QrsRouteInfo record);
}