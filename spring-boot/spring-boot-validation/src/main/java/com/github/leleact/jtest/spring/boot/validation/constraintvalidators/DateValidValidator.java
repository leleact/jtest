package com.github.leleact.jtest.spring.boot.validation.constraintvalidators;

import com.github.leleact.jtest.spring.boot.validation.constraints.DateValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateValidValidator implements ConstraintValidator<DateValid, String> {

    private DateValid annotation;

    @Override
    public void initialize(DateValid constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(annotation.pattern());
        Date date;
        try {
            date = sdf.parse(value);
        } catch (ParseException e) {
            return false;
        }

        String val = sdf.format(date);
        return Objects.equals(val, value);
    }
}
