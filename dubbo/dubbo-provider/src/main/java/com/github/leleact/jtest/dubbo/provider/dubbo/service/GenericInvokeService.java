package com.github.leleact.jtest.dubbo.provider.dubbo.service;

import com.github.leleact.jtest.dubbo.provider.bean.request.GenericRequest;
import com.github.leleact.jtest.dubbo.provider.bean.response.GenericResponse;

public interface GenericInvokeService {

    GenericResponse invoke(GenericRequest request);

}
