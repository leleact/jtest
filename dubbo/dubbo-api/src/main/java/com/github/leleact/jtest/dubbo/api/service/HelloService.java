package com.github.leleact.jtest.dubbo.api.service;

import com.github.leleact.jtest.dubbo.api.bean.request.CommonRequest;
import com.github.leleact.jtest.dubbo.api.bean.response.CommonResponse;

public interface HelloService {

    CommonResponse say1(CommonRequest request);

}
