package com.github.leleact.jtest.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class MapParseTests {

    @Test
    public void parseMap() {
        String s = "{\"a1\":\"b\", \"a2\":13.00}";

        @SuppressWarnings("unchecked")
        Map<String, Object> map = JSONObject.parseObject(s, Map.class);

        Assert.assertEquals("b", map.get("a1").toString());
        Assert.assertEquals(13.00, ((BigDecimal) map.get("a2")).doubleValue(), 0.00005);
    }

    @Test
    public void parseNestMap() {
        String s = "{\"k1\": {\"k11\":\"v11\", \"k12\":13.00}}";

        @SuppressWarnings("unchecked")
        Map<String, Map<String, Object>> map = JSONObject.parseObject(s, Map.class);

        Assert.assertEquals(1, map.size());

        Map<String, Object> nestMap = map.get("k1");
        Assert.assertEquals("v11", nestMap.get("k11"));
        Assert.assertEquals(13.00, ((BigDecimal)nestMap.get("k12")).doubleValue(), 0.00005);
    }

    @Test
    public void parseNestMapNoUnCheckWarnning() {
        String s = "{\"k1\": {\"k11\":\"v11\", \"k12\":13.00}}";

        Map<String, Map<String, Object>> map = JSONObject.parseObject(s,
                new TypeReference<Map<String, Map<String, Object>>>() {
                }.getType());
        Assert.assertEquals(1, map.size());

        Map<String, Object> nestMap = map.get("k1");
        Assert.assertEquals("v11", nestMap.get("k11"));
        Assert.assertEquals(13.00, ((BigDecimal) nestMap.get("k12")).doubleValue(), 0.00005);
    }
}
