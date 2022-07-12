package com.github.leleact.jtest.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Jackson JavaType tests
 *
 * @author leleact
 * @since 2022-07-12
 */
@Slf4j
public class JavaTypeTests {

    private static final ObjectMapper OM = new ObjectMapper();

    @Test
    public void javaTypeSerializeTest() throws JsonProcessingException {
        Pojo p = new Pojo();
        p.setName("abc");
        p.setAge(18);
        JavaType javaType = SimpleType.constructUnsafe(Pojo.class);
        String jsonType = OM.writeValueAsString(javaType);
        String jsonValue = OM.writeValueAsString(p);
        log.info("jsonType: {}", jsonType);
        // transfer via net and other side do not hava the Pojo class
        JavaType resumeType = OM.readValue(jsonType, JavaType.class);
        Pojo resumePojo = OM.readValue(jsonValue, resumeType);
        Assertions.assertEquals("abc", resumePojo.getName());
        Assertions.assertEquals(18, resumePojo.getAge());
    }
}
