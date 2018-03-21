package com.lele.test.testspringbootpropertysource.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class  StaticConfig {

    private static final Logger log = LoggerFactory.getLogger(StaticConfig.class);

    private static String staticField;

    public static String getStaticField() {
        return staticField;
    }

    //OK
    @Value("${a.b.c}")
    public void setStaticField(String b) {
        StaticConfig.staticField = b;
    }

//    not ok
//    public void setStaticField(@Value("${a.b.c}") String b) {
//        StaticConfig.staticField = b;
//    }

    @PostConstruct
    public void postConstructor() {

        log.info("======================>>> {} <<<=========================", staticField);

    }
}
