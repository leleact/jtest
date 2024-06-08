package com.github.leleact.jtest.jdk.type;

import com.github.leleact.jtest.jdk.type.request.SubRequest;
import com.github.leleact.jtest.jdk.type.response.SubResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GenericTypeTests {

    @Disabled
    @Test
    public void interfaceGenericTest() {
        Function<SubRequest, SubResponse> f = new FunctionGeneric<>();
        TypeUtils.showGenericInterfaceType(f.getClass());
        f.apply(new SubRequest());
    }

    @Test
    public void interfaceGenericInstanceTest() {
        // OK can reflect response type
        Function<SubRequest, SubResponse> f = new FunctionGeneric<SubRequest, SubResponse>() {
        };
        TypeUtils.showGenericSuperType(f.getClass());
        f.apply(new SubRequest());
    }

    @Disabled
    @Test
    public void interfaceImplGenericInstanceTest() {
        Function<SubRequest, SubResponse> f = new FunctionGenericImpl() {
        };
        TypeUtils.showType(f.getClass());
        f.apply(new SubRequest());
    }
}
