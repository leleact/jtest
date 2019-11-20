package com.github.leleact.jtest.jdk.type;

import com.github.leleact.jtest.jdk.type.request.SubRequest;
import com.github.leleact.jtest.jdk.type.response.SubResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionGenericImpl extends FunctionGeneric<SubRequest, SubResponse> {

    @Override
    public void showThisClazz() {
        log.debug("Show this class [{}]", this.getClass());
    }

    @Override
    public SubResponse apply(SubRequest subRequest) {
        return super.apply(subRequest);
    }


}
