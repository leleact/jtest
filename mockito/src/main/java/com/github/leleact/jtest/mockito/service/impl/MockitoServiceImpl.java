package com.github.leleact.jtest.mockito.service.impl;

import com.github.leleact.jtest.mockito.entity.MockitoEntity;
import com.github.leleact.jtest.mockito.service.MockitoDependService;
import com.github.leleact.jtest.mockito.service.MockitoService;

/**
 * mockito service implement
 *
 * @author leleact
 * @since 2021-01-20
 */
public class MockitoServiceImpl implements MockitoService {
    private MockitoDependService mockitoDependService;

    @Override
    public void invokedMethod(MockitoEntity entity) {
        entity.setName(this.getClass().getName());
        mockitoDependService.dependMethod(entity);
    }
}
