package com.github.leleact.jtest.jdk.enums;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class EnumTests {

    @Test
    void valueOfTest() {
        Assertions.assertEquals("A", EType.A.name());

        // B的顺序为1
        Assertions.assertEquals(1, EType.B.ordinal());


        EType a = EType.valueOf("A");
        Assertions.assertEquals("a", a.value());

        EType b = EType.valueOf(EType.class, "B");
        Assertions.assertEquals("b", b.value());

        for (EType e : EType.class.getEnumConstants()) {
            log.info("{} : {}", e.name(), e.value());
        }
    }

    @Test
    void constantOfTest() {
        EType a = EType.constantOf("a");
        Assertions.assertEquals("a", a.value());
    }
}
