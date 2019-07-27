package com.github.leleact.jtest.dubbo.api.impl;

import com.github.leleact.jtest.dubbo.api.Api2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Service
public class Api2Impl implements Api2 {

    private static final Logger logger = LoggerFactory.getLogger(Api2Impl.class);

    @Override
    public String api2(String str) {
        logger.info("{}", str);
        return str;
    }
}
