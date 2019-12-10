package com.github.leleact.jtest.spring.boot.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class AopAspect {

    @Around("execution(* com.github.leleact.jtest.spring.boot.aop.service..*(*))")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
