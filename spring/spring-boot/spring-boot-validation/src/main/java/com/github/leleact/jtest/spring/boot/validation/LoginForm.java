package com.github.leleact.jtest.spring.boot.validation;

import com.github.leleact.jtest.spring.boot.validation.constraints.DateValid;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @DateValid(message = "loginDate[${validatedValue}] invalid")
    private String loginDate;

    @DateValid(message = "registerDate[${validatedValue}] invalid")
    private String registerDate;

    @DateValid(pattern = "yyyyMMdd", message = "birthDate[${validatedValue}] invalid")
    private String birthDate;

    @NotEmpty
    @Valid
    private List<Detail> details;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {

        @NotEmpty
        private String name;

        @Min(0)
        @NotNull
        private Integer age;
    }
}
