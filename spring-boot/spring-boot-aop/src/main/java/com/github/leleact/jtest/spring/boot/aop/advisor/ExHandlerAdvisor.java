package com.github.leleact.jtest.spring.boot.aop.advisor;

import com.github.leleact.jtest.spring.boot.aop.annotation.AopEx;
import com.github.leleact.jtest.spring.boot.aop.interceptor.ExHandlerInterceptor1;
import com.github.leleact.jtest.spring.boot.aop.pointcut.AnnotationClassOrMethodPointcut;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.InitializingBean;

public class ExHandlerAdvisor extends AbstractBeanFactoryAwareAdvisingPostProcessor implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setProxyTargetClass(true);
        AbstractPointcutAdvisor advisor = new DefaultPointcutAdvisor(new AnnotationClassOrMethodPointcut(AopEx.class),
                                                                     new ExHandlerInterceptor1());
        // not effect
        advisor.setOrder(-1);
        this.advisor = advisor;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
