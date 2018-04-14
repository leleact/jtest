package com.lele.test.dubbo.starter.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lele.test.dubbo.starter.dubbo.model.HasMapModel;
import com.lele.test.dubbo.starter.dubbo.service.MapTransisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MapTransisServiceImpl implements MapTransisService {

    private static final Logger log = LoggerFactory.getLogger(MapTransisServiceImpl.class);

    @Override
    public HasMapModel transis(HasMapModel mapModel) {
        log.info("receive {}", mapModel);
        return mapModel;
    }
}
