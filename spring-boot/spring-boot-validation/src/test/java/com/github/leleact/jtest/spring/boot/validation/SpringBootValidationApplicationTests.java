package com.github.leleact.jtest.spring.boot.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(post("/login").content("{\"name\":\"aaa\"}").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().is4xxClientError());
    }

    @Test
    public void innerClassValidate() throws Exception {
        LoginForm form = new LoginForm();
        form.setName(UUID.randomUUID().toString().substring(0, 10));
        form.setEmail("abc@gmail.com");
        List<LoginForm.Detail> list = new ArrayList<>();
        LoginForm.Detail detail = new LoginForm.Detail();
        detail.setAge(1);
        list.add(detail);
        form.setDetails(list);
        mockMvc.perform(post("/login").content(objectMapper.writeValueAsString(form))
                                      .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().is4xxClientError());
    }

    @Test
    public void dateValidTest() throws Exception {
        for (int i = 0; i < 3; i++) {
            LoginForm form = new LoginForm();
            form.setName(UUID.randomUUID().toString().substring(0, 10));
            form.setEmail("abc@gmail.com");
            form.setLoginDate("20190230");
            List<LoginForm.Detail> list = new ArrayList<>();
            LoginForm.Detail detail = new LoginForm.Detail();
            detail.setAge(1);
            detail.setName("a");
            list.add(detail);
            form.setDetails(list);
            mockMvc.perform(post("/login").content(objectMapper.writeValueAsString(form))
                                          .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().is4xxClientError());
        }
    }
}
