package com.github.leleact.jtest.validator.customize.constraintvalidators;

import com.github.leleact.jtest.validator.customize.constraints.NewEnum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class NewEnumValidator implements ConstraintValidator<NewEnum, String> {

    private Set<?> values;

    @Override
    public void initialize(NewEnum annotation) {
        if (Objects.isNull(annotation.methodName())) {
            values = Arrays.stream(annotation.enumClass().getEnumConstants())
                           .map(java.lang.Enum::name)
                           .collect(Collectors.toSet());
        } else {
            String methodName = annotation.methodName();
            Method m;
            try {
                m = annotation.enumClass().getMethod(methodName);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            values = Arrays.stream(annotation.enumClass().getEnumConstants()).map(e -> {
                try {
                    return m.invoke(e);
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                }
            }).collect(Collectors.toSet());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }

        return values.contains(value);
    }
}
