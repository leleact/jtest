package com.github.leleact.jtest.spring.boot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.leleact.jtest.spring.boot.web.controller.RequestBodyController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class RequestBodyControllerTests {

    @Resource
    private MockMvc mockMvc;

    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void debugRequestBodyTest() throws Exception {
        RequestBodyController.CommonRequest request = new RequestBodyController.CommonRequest();
        request.setName("a");
        request.setAge(1);
        mockMvc.perform(post("/requestBody/common").contentType(MediaType.APPLICATION_JSON_VALUE)
                                                   .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().string("hello a"));
    }
}
