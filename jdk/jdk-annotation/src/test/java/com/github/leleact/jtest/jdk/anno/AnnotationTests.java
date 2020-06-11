package com.github.leleact.jtest.jdk.anno;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

@Slf4j
public class AnnotationTests {

    @Test
    public void annotationTest() {
        AnnoC annoC = new AnnoC();
        Annotation[] ans = annoC.getClass().getAnnotations();
        for (Annotation an : ans) {
            log.info("Annotation: [{}]", an.toString());
            Class<? extends Annotation> ab = an.annotationType();
            log.info("Class: [{}]", ab.toString());
            Annotation[] abi = ab.getAnnotations();
            for (Annotation aaa : abi) {
                log.info("Annotation: [{}]", aaa.toString());
            }
        }
    }
}
