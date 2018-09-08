package com.github.leleact.test.spring.mvc.test;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PropertiesControllerTests extends BaseTestCase {

    private static final Logger log = LoggerFactory.getLogger(PropertiesControllerTests.class);

    @Test
    public void propClassLoaderTest() throws Exception {
        String response = this.mockMvc.perform(
                get("/prop/classloader").contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"a\":\"1\",\"b\":\"2\"}"))
                .andReturn().getResponse().getContentAsString();

        log.info("response : {}", response);
    }

    @Test
    public void propClassTest() throws Exception {
        String response = this.mockMvc.perform(
                get("/prop/class").contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"a\":\"1\",\"b\":\"2\"}"))
                .andReturn().getResponse().getContentAsString();

        log.info("response : {}", response);
    }
}
