package com.lele.test.dubbo.provider.dubbo.service;

import com.lele.test.dubbo.provider.bean.request.GenericRequest;
import com.lele.test.dubbo.provider.bean.response.GenericResponse;

public interface GenericInvokeService {

    GenericResponse invoke(GenericRequest request);

}
