package com.github.leleact.jtest.spring.boot.aop.pointcut;

import org.springframework.aop.MethodMatcher;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.aop.support.annotation.AnnotationClassFilter;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnnotationClassOrMethodPointcut extends StaticMethodMatcherPointcut {

    private final MethodMatcher methodResolver;

    private final AnnotationClassFilter classFilter;

    public AnnotationClassOrMethodPointcut(Class<? extends Annotation> annotationType) {
        this.methodResolver = new AnnotationMethodMatcher(annotationType);
        classFilter = new AnnotationClassOrMethodFilter(annotationType);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return classFilter.matches(targetClass) || this.methodResolver.matches(method, targetClass);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnnotationClassOrMethodPointcut otherAdvisor)) {
            return false;
        }
        return ObjectUtils.nullSafeEquals(this.methodResolver, otherAdvisor.methodResolver);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private static final class AnnotationClassOrMethodFilter extends AnnotationClassFilter {

        private final AnnotationMethodsResolver methodResolver;

        AnnotationClassOrMethodFilter(Class<? extends Annotation> annotationType) {
            super(annotationType, true);
            this.methodResolver = new AnnotationMethodsResolver(annotationType);
        }

        @Override
        public boolean matches(Class<?> clazz) {
            return super.matches(clazz) || this.methodResolver.hasAnnotatedMethods(clazz);
        }
    }

    private record AnnotationMethodsResolver(Class<? extends Annotation> annotationType) {
        public boolean hasAnnotatedMethods(Class<?> clazz) {
            final AtomicBoolean found = new AtomicBoolean(false);
            ReflectionUtils.doWithMethods(clazz, method -> {
                if (found.get()) {
                    return;
                }
                Annotation annotation = AnnotationUtils.findAnnotation(method, annotationType);
                if (annotation != null) {
                    found.set(true);
                }
            });
            return found.get();
        }
    }
}
