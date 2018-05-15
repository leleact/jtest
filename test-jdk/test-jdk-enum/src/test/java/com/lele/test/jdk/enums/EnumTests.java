package com.lele.test.jdk.enums;

import com.lele.test.numTest.EType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumTests {

    private static final Logger log = LoggerFactory.getLogger(EnumTests.class);

    @Test
    public void valueOfTest(){
        log.info("name:{}", EType.A.name());
        log.info("ordinal:{}", EType.B.ordinal());
        EType e = EType.valueOf(EType.A.name());
    }
}
