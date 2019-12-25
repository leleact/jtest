package com.github.leleact.jtest.spring.boot.validation;

import com.github.leleact.jtest.spring.boot.validation.constraints.DateValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @DateValid(pattern = "yyyyMMdd", message = "loginDate[${validatedValue}] invalid")
    private String loginDate;

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
