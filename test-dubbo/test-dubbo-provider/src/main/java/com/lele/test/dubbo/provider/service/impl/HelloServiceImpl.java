package com.lele.test.dubbo.provider.service.impl;

import com.lele.test.dubbo.api.bean.request.CommonRequest;
import com.lele.test.dubbo.api.bean.response.CommonResponse;
import com.lele.test.dubbo.api.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceImpl implements HelloService {

    private static final Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public CommonResponse say1(CommonRequest request) {
        log.debug("IN:" + request.toString());
        CommonResponse response = new CommonResponse();
        response.setS(request.getS());
        response.setM(request.getM());
        response.setL(request.getL());
        response.setI(request.getI());
        response.setD(request.getD());
        response.setB(request.getB());
        log.debug("OUT:" + response.toString());
        throw new NullPointerException("xxx");
       // return response;
    }
}
