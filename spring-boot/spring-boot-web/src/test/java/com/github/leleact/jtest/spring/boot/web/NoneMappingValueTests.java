package com.github.leleact.jtest.spring.boot.web;

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
public class NoneMappingValueTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void noneMappingValueTest() throws Exception {
        // when expect error will print mock request and response
        // all add .andDo(print())
        mockMvc.perform(get("/none-mapping-value").contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().string("ok"));
    }

}
