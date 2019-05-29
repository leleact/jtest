package com.github.leleact.jtest.spring.boot.validation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * spring boot validation test.
 *
 * @author leleact
 * @since 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class SpringBootValidationApplicationTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    void loginTest() throws Exception {
        mockMvc.perform(post("/login").content("{\"name\":\"aaa\"}").contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(status().is4xxClientError());
    }
}
