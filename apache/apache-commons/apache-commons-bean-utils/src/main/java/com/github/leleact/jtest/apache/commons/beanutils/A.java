package com.github.leleact.jtest.apache.commons.beanutils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class A {

    private String name;

    private List<B> details;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class B {
        private String name;
        private Integer age;
    }
}
