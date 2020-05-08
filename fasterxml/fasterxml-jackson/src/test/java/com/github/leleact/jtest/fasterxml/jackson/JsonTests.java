package com.github.leleact.jtest.fasterxml.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
public class JsonTests {

    private ObjectMapper objectMapper = null;

    @BeforeEach
    private void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void nullValueJsonSerializeTest() {
        Assertions.assertDoesNotThrow(() -> {
            String v = objectMapper.writeValueAsString(null);
            log.info("v: [{}]", v);
        }, "");
    }

    @Test
    public void serializeTest() throws JsonProcessingException {

        Pojo p = new Pojo();
        p.setName("a");
        p.setAge(1);

        String ret = objectMapper.writeValueAsString(p);
        log.info("result = {}", ret);
    }

    @Test
    public void deSerializeTest() throws IOException {

        {
            String str = "{\"name\":\"a\",\"age\":1}";
            Pojo pojo = objectMapper.readValue(str.getBytes(), Pojo.class);

            Assertions.assertEquals(1, pojo.getAge());
            Assertions.assertEquals("a", pojo.getName());
        }

        {
            // primitive type 默认值
            // int -> 0
            // char -> 0
            // float -> 0.0
            // boolean -> false
            String str = "{\"name\":\"a\"}";
            Pojo pojo = objectMapper.readValue(str.getBytes(), Pojo.class);
            log.info("pojo = {}", pojo);
            log.info("c = {}", (int) pojo.getC());
            Assertions.assertEquals(0, pojo.getAge());
        }
    }
}
