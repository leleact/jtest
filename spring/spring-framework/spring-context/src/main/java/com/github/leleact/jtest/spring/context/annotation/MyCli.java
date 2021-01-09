package com.github.leleact.jtest.spring.context.annotation;

import java.lang.annotation.*;

/**
 * custom annotation
 *
 * @author leleact
 * @since 2021-01-09
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MyCli {
    String url();
}
