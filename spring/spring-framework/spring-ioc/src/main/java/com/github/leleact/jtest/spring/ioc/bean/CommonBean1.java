package com.github.leleact.jtest.spring.ioc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


@Component
public class CommonBean1 implements InitializingBean, DisposableBean {

    private static Logger log = LoggerFactory.getLogger(CommonBean1.class);

    private String id;

    private int num;

    public CommonBean1() {
        log.debug("执行构造函数");
    }

    public CommonBean1(String id, int num) {
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
    public void afterPropertiesSet() throws Exception {
        log.debug("InitializingBean afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        log.debug("DisposableBean destroy");
    }
}
