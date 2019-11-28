package com.github.leleact.jtest.dubbo.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ListResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Entity> eList;
}
