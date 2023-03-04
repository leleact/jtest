package com.github.leleact.jtest.spring.boot.web;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpServletTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void servletTest() throws Exception {
        mockMvc.perform(post("/servlet-controller")).andExpect(status().is2xxSuccessful()).andExpect(content().string("ok"));
    }

}
