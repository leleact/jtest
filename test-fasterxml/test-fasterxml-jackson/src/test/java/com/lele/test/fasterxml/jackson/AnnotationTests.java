package com.lele.test.fasterxml.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationTests {

    private static final Logger log = LoggerFactory.getLogger(AnnotationTests.class);

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Bean {
        private String s1;

        private String s2;

        Bean(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }
    }

    @Test
    public void nullValueTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Bean b = new Bean("1", null);
        Assert.assertEquals("{\"s1\":\"1\"}", mapper.writeValueAsString(b));
    }

}
