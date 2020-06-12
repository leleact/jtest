package com.github.leleact.jtest.jdk.type;

import com.github.leleact.jtest.jdk.type.request.GenericRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Slf4j
public class ParameterTypeResolveTests {

    @Test
    public void parameterTypeTest() {
        Assertions.assertDoesNotThrow(() -> {
            Method method = ParameterTypeMethods.class.getMethod("genericReqMethod", GenericRequest.class);
            Type[] types = method.getGenericParameterTypes();
            for (Type type : types) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    log.info("{}'s actual type arguments {}", parameterizedType.getTypeName(), parameterizedType.getActualTypeArguments());
                    log.info("{}'s raw type {}", parameterizedType.getTypeName(), parameterizedType.getRawType());
                    log.info("{}'s owner type {}", parameterizedType.getTypeName(), parameterizedType.getOwnerType());
                }
            }
        });
    }
}
