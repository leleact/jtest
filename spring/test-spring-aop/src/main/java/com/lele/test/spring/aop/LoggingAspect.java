package com.lele.test.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {

    private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    public void logBefore(JoinPoint joinPoint) {
        log.debug("执行前");
    }

    public String logAroundWithArgs(ProceedingJoinPoint joinPoint, String arg1, int arg2) {
        log.debug("log arund");
        log.debug("arg1:" + arg1);
        log.debug("arg2:" + arg2);
        return arg1 + arg2;
    }

    public void logAroundNoArgs(ProceedingJoinPoint joinPoint) {
        log.debug("log arund");
        Object[] objects = joinPoint.getArgs();
        for (Object object : objects) {
            log.debug("arg1:" + object);
        }
        log.debug(joinPoint.getSignature().getDeclaringTypeName());
        // return "就是返回";
    }

    public Object aroundWithNoArgs(ProceedingJoinPoint param) throws Throwable {
        log.info("");

        Object[] args = param.getArgs();
        for(Object o : args) {
            log.info("arg: {}", o.toString());
        }

        log.info(param.getTarget().toString());

        Object retVal = param.proceed(args);

        log.info("");
        return retVal;
    }
}
