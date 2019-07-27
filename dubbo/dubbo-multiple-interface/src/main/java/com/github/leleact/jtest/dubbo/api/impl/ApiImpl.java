package com.github.leleact.jtest.dubbo.api.impl;

import com.github.leleact.jtest.dubbo.api.Api2;
import com.github.leleact.jtest.dubbo.api.Api1;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 错误用法
 * 一个interface对应一个 ServiceBean
 * 当实现多个interface的时候 取第一个
 *
 * @see org.apache.dubbo.config.spring.beans.factory.annotation.ServiceAnnotationBeanPostProcessor
 */
@Service
public class ApiImpl implements Api1, Api2 {

    private static final Logger logger = LoggerFactory.getLogger(ApiImpl.class);

    @Override
    public String api1(String str) {
        logger.info("{}", str);
        return str;
    }

    @Override
    public String api2(String str) {
        logger.info("{}", str);
        return str;
    }
}
