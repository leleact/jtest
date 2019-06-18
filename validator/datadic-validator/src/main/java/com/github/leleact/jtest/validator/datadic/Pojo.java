package com.github.leleact.jtest.validator.datadic;

import lombok.Data;

/**
 * data dictionary.
 *
 * @author leleact
 * @since 1.0
 */
@Data
public class Pojo {

    @ValidateName
    private String name;
}
