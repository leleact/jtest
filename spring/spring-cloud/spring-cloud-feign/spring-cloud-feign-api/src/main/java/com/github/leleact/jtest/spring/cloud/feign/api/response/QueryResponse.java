package com.github.leleact.jtest.spring.cloud.feign.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class QueryResponse<T> extends BaseResponse {

    private Integer pageNum;

    private Integer pages;

    private Long total;

    private List<T> eList;
}
