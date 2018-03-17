package com.lele.test.validator.bean;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorTestor {

    private static final Logger log = LoggerFactory.getLogger(ValidatorTestor.class);

    @Test
    public void validatorTest() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        A a = new A();
   //     a.setE("100");
        Set<ConstraintViolation<A>> violations  =  validator.validate(a);
        for(ConstraintViolation<A> constraintViolation : violations) {
           log.info("{}", constraintViolation.getMessage());
        }
    }
}
