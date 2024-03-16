package com.github.leleact.jtest.servicecomb.server.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * greeting request
 *
 * @author leleact
 * @since 2024-03-16
 */
public class GreetingRequest {
    @NotNull
    private String name;

    private String email;

    @Min(0)
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
