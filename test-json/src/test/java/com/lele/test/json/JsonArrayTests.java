package com.lele.test.json;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;

public class JsonArrayTests {

    private static final Logger log = LoggerFactory.getLogger(JsonArrayTests.class);

    public static class A {
        protected String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }

    public static class B extends A {
        private String b;

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }

    public static class C extends A {
        private String c;

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }

    @Test
    public void parseOneJsonArrayTest() {

        String s = "[{\"a\":\"a1\",\"b\":\"b1\"}]";

        List<Object> list = JSONObject.parseArray(s, new Type[]{B.class, C.class});

        for (Object b : list) {
            if (b instanceof B) {
                log.info("B {}", b);
            } else if (b instanceof C) {
                log.info("C {}", b);
            } else {
                log.info("O {}", b);
            }
        }
    }


    @Test
    public void parseJsonArrayTest() {

        String s = "[{\"a\":\"a1\",\"b\":\"b1\"},{\"a\":\"a2\",\"c\":\"c1\"},{\"a\":\"a3\",\"b\":\"b2\"}]";

        List<Object> list = JSONObject.parseArray(s, new Type[]{B.class, C.class, B.class});

        for (Object b : list) {
            if (b instanceof B) {
                log.info("B {}", b);
            } else if (b instanceof C) {
                log.info("C {}", b);
            } else {
                log.info("O {}", b);
            }
        }
    }
}
