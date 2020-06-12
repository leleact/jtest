package com.github.leleact.jtest.jdk.type;

import com.github.leleact.jtest.jdk.type.request.GenericRequest;
import com.github.leleact.jtest.jdk.type.request.SubRequest;
import lombok.Data;

@Data
public class ParameterTypeMethods {

    public void genericReqMethod(GenericRequest<SubRequest> request) {
    }
}
