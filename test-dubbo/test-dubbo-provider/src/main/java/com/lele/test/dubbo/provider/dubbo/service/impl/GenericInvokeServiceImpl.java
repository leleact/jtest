package com.lele.test.dubbo.provider.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lele.test.dubbo.provider.bean.request.GenericRequest;
import com.lele.test.dubbo.provider.bean.response.GenericResponse;
import com.lele.test.dubbo.provider.dubbo.service.GenericInvokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GenericInvokeServiceImpl implements GenericInvokeService {

    private static final Logger log = LoggerFactory.getLogger(GenericInvokeServiceImpl.class);

    @Override
    public GenericResponse invoke(GenericRequest request) {
        log.info("receive message {}", request);
        GenericResponse response = new GenericResponse();
        response.setName(request.getName());
        response.setAge(request.getAge());
        return response;
    }
}
