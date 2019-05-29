package com.github.leleact.jtest.validator.customize.bean;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
class PersonTest {


    @Test
    void validatorNullDataForSizeAnnotation() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Person p = new Person();

        Set<ConstraintViolation<Person>> violations = validator.validate(p);
        for (ConstraintViolation<Person> constraintViolation : violations) {
            log.info("{}", constraintViolation.getMessage());
        }
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    void validatorNotNullDataForSizeAnnotation() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Person p = new Person();
        p.setName("A");

        Set<ConstraintViolation<Person>> violations = validator.validate(p);
        for (ConstraintViolation<Person> constraintViolation : violations) {
            log.info("constraintViolation constraintDescriptor {}", constraintViolation.getConstraintDescriptor());
            log.info("constraintViolation executableParameters {}", constraintViolation.getExecutableParameters());
            log.info("constraintViolation invalidValue {}", constraintViolation.getInvalidValue());
            log.info("constraintViolation executableReturnValue {}", constraintViolation.getExecutableReturnValue());
            log.info("constraintViolation leafBean {}", constraintViolation.getLeafBean());
            log.info("constraintViolation message {}", constraintViolation.getMessage());
        }
        Assertions.assertEquals(1, violations.size());
    }

}
