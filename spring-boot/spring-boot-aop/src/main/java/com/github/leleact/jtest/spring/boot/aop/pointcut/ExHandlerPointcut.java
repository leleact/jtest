package com.github.leleact.jtest.spring.boot.aop.pointcut;

import com.github.leleact.jtest.spring.boot.aop.annotation.AopEx;
import com.github.leleact.jtest.spring.boot.aop.interceptor.ExHandlerInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

public class ExHandlerPointcut extends AbstractBeanFactoryPointcutAdvisor {

    public ExHandlerPointcut() {
        this.setAdvice(new ExHandlerInterceptor());
    }

    @Override
    public Pointcut getPointcut() {
        return new AnnotationClassOrMethodPointcut(AopEx.class);
    }
}
