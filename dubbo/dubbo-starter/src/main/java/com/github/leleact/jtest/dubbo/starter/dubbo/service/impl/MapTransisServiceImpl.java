package com.github.leleact.jtest.dubbo.starter.dubbo.service.impl;

import com.github.leleact.jtest.dubbo.starter.dubbo.model.HasMapModel;
import com.github.leleact.jtest.dubbo.starter.dubbo.service.MapTransisService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DubboService
public class MapTransisServiceImpl implements MapTransisService {

    private static final Logger log = LoggerFactory.getLogger(MapTransisServiceImpl.class);

    @Override
    public HasMapModel transis(HasMapModel mapModel) {
        log.info("receive {}", mapModel);
        return mapModel;
    }
}
