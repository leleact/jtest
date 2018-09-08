package com.github.leleact.test.spring.mvc.test;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestfulStrTests extends BaseTestCase {
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
