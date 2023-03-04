package com.github.leleact.jtest.validator.customize.bean;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
class ValidatorTestor {


    @Test
    void validatorTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        A a = new A();
        a.setE("80");
        Set<ConstraintViolation<A>> violations = validator.validate(a);
        for (ConstraintViolation<A> constraintViolation : violations) {
            log.info("{}", constraintViolation.getMessage());
        }
    }
}
