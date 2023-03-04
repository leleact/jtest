package com.github.leleact.jtest.spring.boot.validation.constraintvalidators;

import com.github.leleact.jtest.spring.boot.validation.constraints.Enum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

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

        if (clazz.isEnum()) {
            try {
                Method method = getMethod(clazz, annotation);
                if (annotation.isStaticMethod()) {
                    method.invoke(null, value);
                } else {
                    Object[] enums = clazz.getEnumConstants();
                    for (Object item : enums) {
                        Object val = method.invoke(item);
                        if (val.equals(value)) {
                            return true;
                        }
                    }
                    return false;
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                return false;
            }
        }
        return true;
    }

    private Method getMethod(Class<?> clazz, Enum annotation) throws NoSuchMethodException {
        if (annotation.isStaticMethod()) {
            return clazz.getMethod(annotation.methodName(), String.class);
        } else {
            return clazz.getMethod(annotation.methodName());
        }
    }
}
