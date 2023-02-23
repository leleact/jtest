package com.github.leleact.jtest.spring.boot.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionHandleTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void numberFormatExTest() throws Exception {
        mockMvc.perform(get("/a/info").contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().string("NumberFormatException"));
    }
}
