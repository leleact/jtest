package com.github.leleact.jtest.spring.mvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class RequestParamControllerTests {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void requestParamTest() throws Exception {
        String response = mockMvc.perform(
            get("/request/echo").param("sleepTime", "1").content("world")
                                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                                 .andExpect(status().isOk())
                                 .andExpect(content().string("hello1 world"))
                                 .andReturn().getResponse().getContentAsString();
        log.info("response: [{}]", response);
    }

    @Test
    public void postRequestParamTest() throws Exception {
        String response = mockMvc.perform(
            post("/request/echo").param("sleepTime", "1").content("world")
                                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                                 .andExpect(status().isOk())
                                 .andExpect(content().string("hello2 world"))
                                 .andReturn().getResponse().getContentAsString();
        log.info("response: [{}]", response);
    }
}
