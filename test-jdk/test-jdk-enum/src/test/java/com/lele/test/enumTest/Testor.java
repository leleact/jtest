package com.lele.test.enumTest;

import com.lele.test.numTest.EType;
import com.lele.test.numTest.EType1;
import com.lele.test.numTest.Excutor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Testor {

    private static Logger log = LoggerFactory.getLogger(Testor.class);

    @Test
    public void test() {
        Excutor excutor = new Excutor(EType.C);
        excutor.method();
    }

    @Test
    public void test1() {
        log.debug(EType1.A.value());
        log.debug(EType1.B.value());
        log.debug(String.valueOf(EType1.B));
    }
}