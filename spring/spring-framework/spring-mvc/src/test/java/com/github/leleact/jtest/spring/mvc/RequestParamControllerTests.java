package com.github.leleact.jtest.spring.mvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class RequestParamControllerTests extends BaseMvcTests {

    @Test
    void requestParamTest() throws Exception {
        String response = mockMvc.perform(
            get("/request/echo").param("sleepTime", "1").content("world")
                                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                                 .andExpect(status().isOk())
                                 .andExpect(content().string("hello1 world"))
                                 .andReturn().getResponse().getContentAsString();
        log.info("response: [{}]", response);
    }

    @Test
    void postRequestParamTest() throws Exception {
        String response = mockMvc.perform(
            post("/request/echo").param("sleepTime", "1").content("world")
                                 .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                                 .andExpect(status().isOk())
                                 .andExpect(content().string("hello2 world"))
                                 .andReturn().getResponse().getContentAsString();
        log.info("response: [{}]", response);
    }
}
