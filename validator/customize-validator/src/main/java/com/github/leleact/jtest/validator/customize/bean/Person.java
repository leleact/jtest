package com.github.leleact.jtest.validator.customize.bean;

import jakarta.validation.constraints.Size;

public class Person {

    @Size(min = 3, max = 10, message = "名字长度有问题")
    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
