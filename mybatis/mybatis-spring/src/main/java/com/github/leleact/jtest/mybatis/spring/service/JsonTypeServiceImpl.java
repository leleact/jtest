package com.github.leleact.jtest.mybatis.spring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.leleact.jtest.mybatis.spring.db.entity.JsonPojo;
import com.github.leleact.jtest.mybatis.spring.db.entity.TFuncJson;
import com.github.leleact.jtest.mybatis.spring.db.mapper.TFuncJsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * json type service implement.
 *
 * @author leleact
 * @since 2024-08-09
 */
@Slf4j
@Service
public class JsonTypeServiceImpl implements JsonTypeService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final TFuncJsonMapper mapper;

    public JsonTypeServiceImpl(TFuncJsonMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void executor() {
        try {
            TFuncJson pojo = new TFuncJson();
            pojo.setTid(1L);
            List<JsonPojo> list = new ArrayList<>();
            {
                JsonPojo pojo1 = new JsonPojo();
                pojo1.setId("a");
                pojo1.setValue("b");
                list.add(pojo1);
            }
            pojo.setData(objectMapper.readTree(objectMapper.writeValueAsString(list)));
            pojo.setCreateTime(new Date());
            pojo.setLastUpdateTime(new Date());
            mapper.insert(pojo);
            TFuncJson result = mapper.selectByPrimaryKey(1L);
            log.info(objectMapper.writeValueAsString(result));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            mapper.deleteByPrimaryKey(1L);
        }
    }
}
