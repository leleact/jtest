package com.lele.test.validator.bean;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    private static final Logger log = LoggerFactory.getLogger(PersonTest.class);

    @Test
    public void validatorNullDataForSizeAnnotation() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Person p = new Person();

        Set<ConstraintViolation<Person>> violations  =  validator.validate(p);
        for(ConstraintViolation<Person> constraintViolation : violations) {
            log.info("{}", constraintViolation.getMessage());
        }
        assertEquals(0, violations.size());
    }

    @Test
    public void validatorNotNullDataForSizeAnnotation() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Person p = new Person();
        p.setName("A");

        Set<ConstraintViolation<Person>> violations  =  validator.validate(p);
        for(ConstraintViolation<Person> constraintViolation : violations) {
            log.info("constraintViolation constraintDescriptor {}", constraintViolation.getConstraintDescriptor());
            log.info("constraintViolation executableParameters {}", constraintViolation.getExecutableParameters());
            log.info("constraintViolation invalidValue {}", constraintViolation.getInvalidValue());
            log.info("constraintViolation executableReturnValue {}", constraintViolation.getExecutableReturnValue());
            log.info("constraintViolation leafBean {}", constraintViolation.getLeafBean());
            log.info("constraintViolation message {}", constraintViolation.getMessage());
        }
        assertEquals(1, violations.size());
    }

}