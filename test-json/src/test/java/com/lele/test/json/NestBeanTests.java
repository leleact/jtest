package com.lele.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public class NestBeanTests {

    private static final Logger logger = LoggerFactory.getLogger(NestBeanTests.class);

    public static class Base {
    }

    public static class Derived1 extends Base {
        private String f1;

        public String getF1() {
            return f1;
        }

        public void setF1(String f1) {
            this.f1 = f1;
        }
    }

    public static class Derived2 extends Base {
        private String f2;

        public String getF2() {
            return f2;
        }

        public void setF2(String f2) {
            this.f2 = f2;
        }
    }

    public static class JBean<T extends Base> {

        private T base;

        public T getBase() {
            return base;
        }

        public void setBase(T base) {
            this.base = base;
        }
    }

    @Test
    public void serializeTest() {
        JBean<Derived1> jBean = new JBean<>();
        Derived1 b = new Derived1();
        b.setF1("a");
        jBean.setBase(b);

        String s = JSON.toJSONString(jBean);
        logger.info("{}", s);
    }

    final static Type type = new TypeReference<JBean<Derived1>>() {
    }.getType();

    @Test
    public void derializeTest() {


        String s = "{\"base\":{\"f1\":\"a\"}}";


        JBean<Derived1> jBean = JSON.parseObject(s, type);

        logger.info("{}", JSON.toJSONString(jBean));
    }
}
