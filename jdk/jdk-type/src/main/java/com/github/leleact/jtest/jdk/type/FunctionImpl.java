package com.github.leleact.jtest.jdk.type;

import com.github.leleact.jtest.jdk.type.request.BaseRequest;
import com.github.leleact.jtest.jdk.type.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionImpl implements Function<BaseRequest, BaseResponse> {

    @Override
    public BaseResponse apply(BaseRequest baseRequest) {
        TypeUtils.showType(this.getClass());
        return new BaseResponse();
    }
}
