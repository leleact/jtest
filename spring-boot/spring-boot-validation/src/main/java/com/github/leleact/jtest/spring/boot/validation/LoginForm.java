package com.github.leleact.jtest.spring.boot.validation;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * login form.
 *
 * @author leleact
 * @since 1.0
 */
@Data
public class LoginForm {

    @NotEmpty(message = "{name.not.empty}")
    @Size(min = 8, max = 32, message = "{user.name.size.illegal}")
    private String name;

    @NotNull
    private String email;
}
