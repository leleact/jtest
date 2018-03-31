package com.lele.test.spring.ioc.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class LifeCycleTestBean implements InitializingBean, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(LifeCycleTestBean.class);

    private String field;

    // @1
    public LifeCycleTestBean() {
        log.debug("LifeCycleTestBean construct invoked");
    }

    // @5
    public void init() {
        log.debug("init method invoked");
    }

    // @8
    public void destroyMethod() {
        log.debug("destroyMethod method invoked");
    }

    // @4
    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("afterPropertiesSet method invoked");
    }

    // @7
    @Override
    public void destroy() throws Exception {
        log.debug("destroy method invoked");
    }

    // @3
    @PostConstruct
    public void postConstruct() {
        log.debug("postConstruct method invoked, field value : {}", getField());
    }

    // @6
    @PreDestroy
    public void preDestroy() {
        log.debug("preDestroy method invoked");
    }

    public String getField() {
        return field;
    }

    // @2
    @Value("${field:f}")
    public void setField(String field) {
        log.debug("setField method invoked, field will be set as value : {}", field);
        this.field = field;
    }
}
