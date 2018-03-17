package com.lele.test.database.mysql.bean.mapper;

import com.lele.test.database.mysql.bean.dto.SequencePerDay;

public interface SequencePerDayMapper {
    int deleteByPrimaryKey(String name);

    int insert(SequencePerDay record);

    int insertSelective(SequencePerDay record);

    SequencePerDay selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(SequencePerDay record);

    int updateByPrimaryKey(SequencePerDay record);

    int selectMchtNo();
}