package com.github.leleact.jtest.dubbo.provider.dubbo.service.impl;

import com.github.leleact.jtest.dubbo.provider.bean.request.GenericRequest;
import com.github.leleact.jtest.dubbo.provider.bean.response.GenericResponse;
import com.github.leleact.jtest.dubbo.provider.dubbo.service.GenericInvokeService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GenericInvokeServiceImpl implements GenericInvokeService {

    private static final Logger log = LoggerFactory.getLogger(GenericInvokeServiceImpl.class);

    @Override
    public GenericResponse invoke(GenericRequest request) {
        log.info("receive a message {}", request);
        GenericResponse response = new GenericResponse();
        response.setName(request.getName());
        response.setAge(request.getAge());
        return response;
    }
}
