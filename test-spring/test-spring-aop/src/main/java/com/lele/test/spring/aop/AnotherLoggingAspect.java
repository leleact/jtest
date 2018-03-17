package com.lele.test.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnotherLoggingAspect {

    private static Logger log = LoggerFactory.getLogger(AnotherLoggingAspect.class);

    public void logBefore(JoinPoint joinPoint) {
        log.debug("执行前");
    }

    public String logAroundWithArgs(JoinPoint joinPoint, String arg1, int arg2) {
        log.debug("log arund");
        return arg1 + arg2;
    }

    public String logAroundNoArgs(JoinPoint joinPoint) {
        log.debug("log arund");
        return "就是返回";
    }
}