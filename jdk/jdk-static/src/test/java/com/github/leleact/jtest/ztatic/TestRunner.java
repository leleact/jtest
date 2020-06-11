package com.github.leleact.jtest.ztatic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestRunner {

    @Test
    public void staticTest() {
        String str = UsedStaticField.field;
        Assertions.assertEquals("XXX", str);
    }

    @Test
    public void staticFiledWithMapTest() {
        Assertions.assertDoesNotThrow(() -> {
            StaticMap.staticMap.put("AAA", "aaa");
            log.debug(StaticConstant.StaticFiled.filed + "," + StaticMap.staticMap);

            StaticMap.staticMap.put("AAA", "aaa1");
            log.debug(StaticConstant.StaticFiled.filed + "," + StaticMap.staticMap);
        });
    }
}
