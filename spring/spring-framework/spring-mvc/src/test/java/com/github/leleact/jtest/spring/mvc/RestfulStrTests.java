package com.github.leleact.jtest.spring.mvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class RestfulStrTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void retStrTest() throws Exception {
        String response = this.mockMvc.perform(
            post("/retstr").contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                                      .andExpect(status().isOk())
                                      .andExpect(content().string("string value"))
                                      .andReturn().getResponse().getContentAsString();
        System.out.println(response);
    }

    @Test
    public void pathVariableControllerTest() throws Exception {
        String response = this.mockMvc.perform(
            post("/111/info").contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                                      .andExpect(status().isOk())
                                      .andExpect(content().string("ok"))
                                      .andReturn().getResponse().getContentAsString();
        System.out.println(response);
    }
}
