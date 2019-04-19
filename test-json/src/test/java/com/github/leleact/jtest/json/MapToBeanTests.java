package com.github.leleact.jtest.json;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MapToBeanTests {

    public static class TestBean {

        private String id;

        private Integer count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    };

    @Test
    public void mapToBeanTest() {

        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID().toString().replace("-", ""));
        map.put("count", 32);

        JSONObject object = new JSONObject(map);
        TestBean bean = object.toJavaObject(TestBean.class);
        Assert.assertEquals(32, bean.getId().length());
        Assert.assertEquals(32L, (long)bean.getCount());
    }
}
