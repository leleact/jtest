package com.github.leleact.jtest.spring.boot.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class DataBindTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void dataTest() throws Exception {
        String data = "<a><b>b</b><c>c</c></a>";
        String response = mockMvc.perform(
            post("/xml").contentType(MediaType.TEXT_XML_VALUE + ";charset=utf-8").content(
                data)).andExpect(
            status().is(200)).andReturn().getResponse().getContentAsString();
        log.info("response : [{}]", response);
    }
}
