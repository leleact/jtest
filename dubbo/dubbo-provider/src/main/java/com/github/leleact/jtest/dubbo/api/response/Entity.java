package com.github.leleact.jtest.dubbo.api.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private int age;
}
