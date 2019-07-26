package com.lele.test.dubbo.starter.dubbo.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Map;

public class HasMapModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

    private Map<String, String> mm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<String, String> getMm() {
        return mm;
    }

    public void setMm(Map<String, String> mm) {
        this.mm = mm;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
