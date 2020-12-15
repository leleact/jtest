package com.github.leleact.jtest.spring.ioc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class CommonBean implements BeanPostProcessor, InitializingBean, DisposableBean {

    private static Logger log = LoggerFactory.getLogger(CommonBean.class);

    private String id;

    private int num;

    public CommonBean() {
        log.debug("执行构造函数");
    }

    public CommonBean(String id, int num) {
        this.id = id;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        log.debug("setId id:" + id);
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        log.debug("setNum num:" + num);
        this.num = num;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug("post before initailization {}", bean.getClass().toString());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("post after initailization {}", bean.getClass().toString());
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("InitializingBean afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        log.debug("DisposableBean destroy");
    }
}
