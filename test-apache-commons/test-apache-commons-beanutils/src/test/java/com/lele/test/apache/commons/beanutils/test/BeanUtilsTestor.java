package com.lele.test.apache.commons.beanutils.test;

import com.lele.test.apache.commons.beanutils.Order;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilsTestor {

    private static final Logger log = LoggerFactory.getLogger(BeanUtilsTestor.class);

    @Test
    public void orderTest() throws InvocationTargetException, IllegalAccessException {

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
    public void orderMergeTest() throws InvocationTargetException, IllegalAccessException {

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
                if(value==null) {
                    return;
                }
                super.copyProperty(bean, name, value);
            }
        }.copyProperties(o1, o2);

        log.info("o1 = {}", o1);
    }
}
