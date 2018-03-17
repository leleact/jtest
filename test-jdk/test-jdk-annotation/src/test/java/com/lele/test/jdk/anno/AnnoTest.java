package com.lele.test.jdk.anno;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class AnnoTest {

    private static final Logger log = LoggerFactory.getLogger(AnnoTest.class);

    @Test
    public void annoTest() {

        AnnoC annoC = new AnnoC();
        Annotation[] ans = annoC.getClass().getAnnotations();
        for (Annotation an : ans) {
            log.info("Annotation: [{}]", an.toString());
            Class<? extends Annotation> ab = an.annotationType();
            log.info("Class: [{}]", ab.toString());
            Annotation[] abi = ab.getAnnotations();
            for(Annotation aaa : abi) {
                log.info("Annotation: [{}]", aaa.toString());
            }
        }
    }
}
