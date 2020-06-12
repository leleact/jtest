package com.github.leleact.jtest.jdk.type.request;

import lombok.Data;

@Data
public class GenericRequest<T> {

    private BaseRequest request;

    private  T wrapperObj;

}
