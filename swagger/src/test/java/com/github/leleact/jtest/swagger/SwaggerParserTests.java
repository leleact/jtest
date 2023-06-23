package com.github.leleact.jtest.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * swagger parse test
 *
 * @author leleact
 * @since 2023-06-23
 */
@Slf4j
public class SwaggerParserTests {

    private static final ObjectMapper OM = new ObjectMapper();

    @Test
    public void swaggerParseTest() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("META-INF/open-api/doc.json")) {
            if (is == null) {
                log.error("read json file error");
                return;
            }
            IOUtils.copy(is, bo);
        }
        String swaggerContent = bo.toString(StandardCharsets.UTF_8);
        OpenAPI api = OM.readValue(swaggerContent, OpenAPI.class);
        // org.apache.servicecomb.common.javassist.JavassistUtils#createCtClass
        api.getComponents().getSchemas().forEach((k, v) -> {
            log.info("model:{}, class: {}, property: {}", k, v.getClass(), v.getProperties());
        });
    }
}
