package com.github.leleact.jtest.spring.cloud.feign.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class QueryRequest extends BaseRequest {

    private Integer page;

    private Integer rows;

    private String orderBy;

}
