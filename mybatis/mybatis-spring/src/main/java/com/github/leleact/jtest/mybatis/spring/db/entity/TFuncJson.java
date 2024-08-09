package com.github.leleact.jtest.mybatis.spring.db.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.Date;

/**
 * T1 entity
 *
 * @author leleact
 * @since 2021-01-09
 */
@Data
public class TFuncJson {
    private Long tid;

    private JsonNode data;

    private Date createTime;

    private Date lastUpdateTime;
}
