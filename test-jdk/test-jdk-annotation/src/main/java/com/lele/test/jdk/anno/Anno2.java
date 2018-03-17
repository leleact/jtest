package com.lele.test.jdk.anno;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Anno2 {
    boolean multiple() default false;
}
