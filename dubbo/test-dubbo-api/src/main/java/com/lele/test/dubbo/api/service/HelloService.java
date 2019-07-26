package com.lele.test.dubbo.api.service;

import com.lele.test.dubbo.api.bean.request.CommonRequest;
import com.lele.test.dubbo.api.bean.response.CommonResponse;

public interface HelloService {

    CommonResponse say1(CommonRequest request);

}
