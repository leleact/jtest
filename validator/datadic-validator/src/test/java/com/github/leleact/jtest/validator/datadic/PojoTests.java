package com.github.leleact.jtest.validator.datadic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

/**
 * pojo validate test.
 *
 * @author leleact
 * @since 1.0
 */
@Slf4j
public class PojoTests {

    @Test
    public void validatorTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Pojo p = new Pojo();
        p.setName("a");

        Set<ConstraintViolation<Pojo>> violations = validator.validate(p);
        for (ConstraintViolation<Pojo> constraintViolation : violations) {
            log.info("{}", constraintViolation.getMessage());
        }
    }
}
