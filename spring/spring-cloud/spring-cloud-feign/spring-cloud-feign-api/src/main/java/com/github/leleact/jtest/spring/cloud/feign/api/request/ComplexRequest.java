package com.github.leleact.jtest.spring.cloud.feign.api.request;

import lombok.Data;

/**
 * complex request.
 *
 * @author leleact
 */
@Data
public class ComplexRequest {

    @Data
    public static class Color {
        private String name;
        private int r;
        private int g;
        private int b;
    }

    @Data
    public static class Animal {
        private String name;
        private int legs;
        private String attr;
    }

    private Color color;
    private Animal animal;
}
