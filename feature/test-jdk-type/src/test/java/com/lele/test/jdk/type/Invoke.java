package com.lele.test.jdk.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;

public class Invoke<RES extends Request, REQ extends Response> {

    private static final Logger log = LoggerFactory.getLogger(Invoke.class);

    REQ handle(RES res) {
        log.info(this.getClass().getGenericSuperclass().getTypeName());
        log.info(
                ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName());
        return null;
    }
}
