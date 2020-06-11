package com.github.leleact.jtest.spring.boot.aop.pointcut;

import com.github.leleact.jtest.spring.boot.aop.annotation.AopEx;
import com.github.leleact.jtest.spring.boot.aop.interceptor.ExHandlerInterceptor2;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

public class ExHandlerPointcut extends AbstractBeanFactoryPointcutAdvisor {

    static final long serialVersionUID = 1L;

    public ExHandlerPointcut() {
        this.setAdvice(new ExHandlerInterceptor2());
    }

    @Override
    public Pointcut getPointcut() {
        return new AnnotationClassOrMethodPointcut(AopEx.class);
    }
}
