package com.github.leleact.test.dubbo.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.leleact.test.dubbo.api.Api1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@Service
public class Api1Impl implements Api1 {

    private static final Logger logger = LoggerFactory.getLogger(Api1Impl.class);

    @Override
    public String api1(String str) {
        logger.info("{}", str);
        return str;
    }
}
