package com.lele.test.jdk.enums;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumTests {

    private static final Logger log = LoggerFactory.getLogger(EnumTests.class);

    @Test
    public void valueOfTest(){
        Assert.assertEquals("A", EType.A.name());

        // B的顺序为1
        Assert.assertEquals(1, EType.B.ordinal());


        EType a = EType.valueOf("A");
        Assert.assertEquals(0, a.value());

        EType b = EType.valueOf(EType.class, "B");
        Assert.assertEquals(10, b.value());

        EType c = EType.valueOf(3);
        Assert.assertEquals(3, c.value());

        for (EType e : EType.class.getEnumConstants()) {
            log.info("{} : {}", e.name(), e.value());
        }

        log.info(EType2.A.name());
    }
}
