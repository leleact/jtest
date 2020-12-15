package com.github.leleact.jtest.spring.mvc;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class PropertiesControllerTests {

    @Resource
    private MockMvc mockMvc;

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
