package com.github.leleact.jtest.apache.commons.beanutils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class BeanUtilsDescribeTests {

    @Test
    public void describeTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        A a = new A();
        a.setName("a");
        a.setDetails(Arrays.asList(new A.B("b1", 1), new A.B("b2", 2)));
        Map<String, String> map = BeanUtils.describe(a);
        log.info("describe class A: [{}]", map);
        Assertions.assertNotNull(map);
    }
}
