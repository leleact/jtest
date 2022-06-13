package com.github.leleact.jtest.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * generic test
 *
 * @author leleact
 * @since 2022-06-14
 */
@Slf4j
public class GenericTypeTests {
    @Data
    public static class GPojo<T> {
        private String id;
        private T entity;
    }


    @Data
    public static class Pojo {
        private String name;

        private Integer age;
    }

    @Data
    public static class X {
        private String xName;

        private GPojo<Pojo> pojo;
    }

    private static final ObjectMapper OM = new ObjectMapper();

    @Test
    public void serializeTest() throws JsonProcessingException {
        Pojo pojo = new Pojo();
        pojo.setName("Lilith");
        pojo.setAge(10);
        GPojo<Pojo> p = new GPojo<>();
        p.setId("xId");
        p.setEntity(pojo);
        X x = new X();
        x.setXName("xxx");
        x.setPojo(p);
        String json = OM.writeValueAsString(x);
        log.info("{}", json);
        X x1 = OM.readValue(json, X.class);
        log.info("{}", x1.getPojo().entity);
    }

    @Test
    public void deserializeTest() throws JsonProcessingException {
        String json = "{\"pojo\":{\"id\":\"xId\",\"entity\":{\"name\":\"Lilith\",\"age\":10}},\"xname\":\"xxx\"}";
        log.info("{}", json);
        X x = OM.readValue(json, X.class);
        log.info("{}", x.getPojo().entity);
    }

}
