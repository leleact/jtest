package com.lele.test.jdk.anno;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Anno1
@Anno2
public @interface Anno3 {

    @AliasFor(annotation = Anno1.class, attribute = "basePackages")
    String[] scanBasePackages() default {};

    @AliasFor(annotation = Anno1.class, attribute = "basePackageClasses")
    Class<?>[] scanBasePackageClasses() default {};

    @AliasFor(annotation = Anno2.class, attribute = "multiple")
    boolean multipleConfig() default false;
}
