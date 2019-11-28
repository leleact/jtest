package com.github.leleact.jtest.dubbo.starter.dubbo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class HasMapModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

    private Map<String, String> mm;
}
