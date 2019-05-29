package com.github.leleact.jtest.validator.customize.bean;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
class ValidatorTestor {


    @Test
    void validatorTest() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        A a = new A();

        Set<ConstraintViolation<A>> violations = validator.validate(a);
        for (ConstraintViolation<A> constraintViolation : violations) {
            log.info("{}", constraintViolation.getMessage());
        }
    }
}
