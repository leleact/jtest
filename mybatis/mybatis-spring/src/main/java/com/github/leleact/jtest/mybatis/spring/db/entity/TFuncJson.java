package com.github.leleact.jtest.mybatis.spring.db.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * T1 entity
 *
 * @author leleact
 * @since 2021-01-09
 */
@Data
public class TFuncJson {
    private Long tid;

    private List<JsonPojo> data;

    private Date createTime;

    private Date lastUpdateTime;
}
