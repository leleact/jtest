package com.github.leleact.jtest.spring.boot.aop.interceptor;

import com.github.leleact.jtest.spring.boot.aop.annotation.AopEx;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class ExHandlerInterceptor2 implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("yyy");
        AopEx ex = invocation.getMethod().getAnnotation(AopEx.class);
        if (ex != null) {
            Class<? extends Throwable> exceptionType = ex.value();
            log.info("{}", exceptionType);
        }
        return invocation.proceed();
    }
}
