package com.github.leleact.jtest.spring.boot.aop.aspect;

import com.github.leleact.jtest.spring.boot.aop.annotation.AopEx;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@Order(0)
public class AopAspect {

    @Around("execution(* com.github.leleact.jtest.spring.boot.aop.service..*(*))")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        showAnnotations(joinPoint);
        return joinPoint.proceed();
    }

    private void showAnnotations(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AopEx myAnnotation = method.getAnnotation(AopEx.class);
        log.info("annotation: [{}]", myAnnotation);
    }
}
