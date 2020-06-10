package com.github.leleact.jtest.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

@Slf4j
public class DateFormatTests {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Data
    private static class Pojo1 {
        private Date date;
    }

    @Data
    private static class Pojo2 {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date date;
    }

    @Test
    public void serializeTest() {
        Assertions.assertDoesNotThrow(() -> {
            {
                Pojo1 pojo1 = new Pojo1();
                pojo1.setDate(new Date());
                String json = objectMapper.writeValueAsString(pojo1);
                log.info("pojo1 = {}", json);
            }
            {
                Pojo2 pojo2 = new Pojo2();
                pojo2.setDate(new Date());
                String json = objectMapper.writeValueAsString(pojo2);
                log.info("pojo2 = {}", json);
            }
        });

    }

    @Test
    public void deSerializeTest() {
        Assertions.assertDoesNotThrow(() -> {
            {
                String json = "{\"date\":1591778560889}";
                Pojo1 pojo1 = objectMapper.readValue(json, Pojo1.class);
                log.info("pojo1 = {}", pojo1);

                Pojo2 pojo2 = objectMapper.readValue(json, Pojo2.class);
                log.info("pojo1 = {}", pojo2);
            }
        });
        Assertions.assertThrows(Exception.class, () -> {
            {
                String json = "{\"date\":\"2020-06-10 08:42:40\"}";
                Pojo1 pojo1 = objectMapper.readValue(json, Pojo1.class);
                log.info("pojo1 = {}", pojo1);

                Pojo2 pojo2 = objectMapper.readValue(json, Pojo2.class);
                log.info("pojo1 = {}", pojo2);
            }
        });
    }
}
