package com.github.leleact.jtest.dubbo.provider.dubbo.service.impl;

import com.github.leleact.jtest.dubbo.api.MultipleVerApi;
import com.github.leleact.jtest.dubbo.api.response.Entity;
import com.github.leleact.jtest.dubbo.api.response.ListResponse;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MultipleVerApiImpl implements MultipleVerApi {
    @Override
    public ListResponse getResponse() {
        ListResponse response = new ListResponse();
        List<Entity> entities = new ArrayList<>();
        Entity e = new Entity();
        e.setName("a");
        e.setAge(1);
        entities.add(e);
        response.setEList(entities);
        return response;
    }
}
