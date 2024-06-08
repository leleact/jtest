package com.github.leleact.jtest.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

@Slf4j
public class AnotherLoggingAspect {

    public void logBefore(JoinPoint joinPoint) {
        log.debug("执行前");
    }

    public String logAroundWithArgs(JoinPoint joinPoint, String arg1, Integer arg2) {
        log.debug("log arund");
        return arg1 + arg2;
    }

    public String logAroundNoArgs(JoinPoint joinPoint) {
        log.debug("log arund");
        return "就是返回";
    }
}
