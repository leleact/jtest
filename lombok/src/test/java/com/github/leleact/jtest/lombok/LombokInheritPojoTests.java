package com.github.leleact.jtest.lombok;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * lombok inherit test.
 *
 * @author leleact
 * @since 1.0
 */
@Slf4j
class LombokInheritPojoTests {


    @Data
    class Base {

        private String f1;

        private String f2;

    }

    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    class Derive extends Base {

        private Integer i1;

        private Integer i2;

    }

    @Test
    void toStringTest() {

        Derive d = new Derive();
        d.setI1(1);
        d.setI2(2);
        d.setF1("f1");
        d.setF2("f2");

        log.info("derive : {}", d);
    }


    // 当没有  @EqualsAndHashCode(callSuper = true) 的时候， d1和d2相同
    @Test
    void hashCodeTest() {

        Derive d1 = new Derive();
        d1.setI1(1);
        d1.setI2(2);
        d1.setF1("f1");
        d1.setF2("f2");

        Derive d2 = new Derive();
        d2.setI1(1);
        d2.setI2(2);
        d2.setF1("f11");
        d2.setF2("f2");
        Assertions.assertNotEquals(d1.hashCode(), d2.hashCode());
    }

}
