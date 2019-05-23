package com.github.leleact.jtest.apache.commons.beanutils.test;

import com.github.leleact.jtest.apache.commons.beanutils.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.junit.jupiter.api.Test;


import java.lang.reflect.InvocationTargetException;

@Slf4j
class BeanUtilsTests {

    @Test
    void orderTest() throws InvocationTargetException, IllegalAccessException {

        Order o1 = new Order();
        o1.setPri("p");
        o1.setF1("f1");

        log.info("{}", o1);


        Order o2 = new Order();
        o2.setPri("p");
        o2.setF2("f2");

        log.info("{}", o2);


        BeanUtils.copyProperties(o1, o2);

        log.info("{}", o1);
    }

    // 并不能merge
    @Test
    void orderMergeTest() throws InvocationTargetException, IllegalAccessException {

        Order o1 = new Order();
        o1.setPri("p");
        o1.setF1("f1");

        log.info("o1 = {}", o1);


        Order o2 = new Order();
        o2.setPri("p");
        o2.setF2("f2");

        log.info("o2 = {}", o2);

        new BeanUtilsBean() {
            @Override
            public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException,
                InvocationTargetException {
                if (value == null) {
                    return;
                }
                super.copyProperty(bean, name, value);
            }
        }.copyProperties(o1, o2);

        log.info("o1 = {}", o1);
    }
}
