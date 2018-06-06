package com.lele.test.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonObjectToMapTests {

    @Test
    public void objectToMap() {

        List<String> list = new ArrayList<>();

        list.add("11");
        list.add("22");

        Object obj = JSONObject.toJSON(list);  // JSONArray

        Assert.assertTrue( (obj instanceof JSONArray) );

    }

}
