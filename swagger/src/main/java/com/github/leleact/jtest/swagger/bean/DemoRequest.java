package com.github.leleact.jtest.swagger.bean;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

/**
 * request
 *
 * @author leleact
 * @since 2023-06-23
 */
@Setter
@Getter
public class DemoRequest {
    @NotBlank
    private String name;

    private Integer age;

    private String address;

    private String email;

    private String phone;

    private DemoResponse response;
}
