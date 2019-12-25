package com.github.leleact.jtest.spring.boot.validation.constraints;

import com.github.leleact.jtest.spring.boot.validation.constraintvalidators.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {EnumValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull
public @interface Enum {
    String message() default "{javax.validation.constraints.Enum.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends java.lang.Enum<?>> enumClass();

    boolean ignoreCase() default false;

    String methodName() default "value";

    boolean isStaticMethod() default false;

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        Enum[] value();
    }
}
