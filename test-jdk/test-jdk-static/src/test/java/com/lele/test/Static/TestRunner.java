package com.lele.test.Static;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestRunner {

    private static Logger log = LoggerFactory.getLogger(TestRunner.class);

    @Test
    public void StaticTest() {
        String str = UsedStaticField.field;
    }

    @Test
    public void staticFiledwithMap() {
        StaticMap.staticMap.put("AAA", "aaa");
        log.debug(StaticConstant.StaticFiled.filed + "," + StaticMap.staticMap);


        StaticMap.staticMap.put("AAA", "aaa1");
        log.debug(StaticConstant.StaticFiled.filed + "," + StaticMap.staticMap);
    }
}
