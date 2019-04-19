package com.github.leleact.jtest.json.bean;

import com.alibaba.fastjson.JSONObject;

public class PersonHasJsonObj {

    private String name;

    private int age;

    JSONObject jsonObject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
