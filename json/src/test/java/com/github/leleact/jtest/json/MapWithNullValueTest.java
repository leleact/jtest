package com.github.leleact.jtest.json;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MapWithNullValueTest {

    private static Logger log = LoggerFactory.getLogger(MapWithNullValueTest.class);

    @Test
    public void mapWithNullValuetoJson() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", null);
        log.debug(JSONObject.toJSONString(map));
    }

}
