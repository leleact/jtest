package com.github.leleact.jtest.validator.datadic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * pojo validate test.
 *
 * @author leleact
 * @since 1.0
 */
@Slf4j
class PojoTests {

    @Test
    void validatorTest() {
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
