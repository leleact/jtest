package com.github.leleact.jtest.validator.customize.constraintvalidators;

import com.github.leleact.jtest.validator.customize.constraints.Enum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnumValidator implements ConstraintValidator<Enum, String> {

    private Enum annotation;

    @Override
    public void initialize(Enum constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (null == value) {
            return false;
        }

        Class<?> clazz = this.annotation.enumClass();
        Method method = null;
        try {
            clazz.isEnum();
            method = clazz.getMethod("constantOf", String.class);
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }

        try {
            method.invoke(null, value);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            return false;
        }
        return true;
    }
}
