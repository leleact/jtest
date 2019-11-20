package com.github.leleact.jtest.jdk.type;

import com.github.leleact.jtest.jdk.type.request.BaseRequest;
import com.github.leleact.jtest.jdk.type.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;

@Slf4j
public class FunctionGeneric<T extends BaseRequest, U extends BaseResponse> implements Function<T, U> {

    public void showThisClazz() {
        log.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Show this class [{}]", this.getClass());
    }

    @Override
    public U apply(T t) {
        TypeUtils.showType(this.getClass());
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        showThisClazz();
        Class<U> c = (Class<U>) type.getActualTypeArguments()[1];
        try {
            return c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
