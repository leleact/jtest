package com.github.leleact.jtest.spring.mvc;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class PostControllerTests extends BaseMvcTests {

    @Test
    void postJsonTest() throws Exception {
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("a", "b");
        reqMap.put("c", "d");
        String reqStr = objectMapper.writeValueAsString(reqMap);
        String response = this.mockMvc.perform(
            post("/post/map").contentType(MediaType.APPLICATION_JSON_VALUE).content(reqStr))
                                      .andExpect(status().isOk())
                                      .andReturn().getResponse().getContentAsString();
        log.info("response: {}", response);
    }

    @Test
    void postQueryForm() throws Exception {
        String response = this.mockMvc.perform(
            post("/post/map?a=b&c=d").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                                      .andExpect(status().isOk())
                                      .andReturn().getResponse().getContentAsString();
        log.info("response: {}", response);
    }

    @Test
    void postUrlEnForm() throws Exception {
        String response = this.mockMvc.perform(
            post("/post/map").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                             .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                                 new BasicNameValuePair("a", "b"),
                                 new BasicNameValuePair("c", "c")), Consts.UTF_8
                             ))))
                                      .andExpect(status().isOk())
                                      .andReturn().getResponse().getContentAsString();
        log.info("response: {}", response);
    }

    @Test
    void postParamForm() throws Exception {
        String response = this.mockMvc.perform(
            post("/post/map").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                             .param("a", "b")
                             .param("c", "d"))
                                      .andExpect(status().isOk())
                                      .andReturn().getResponse().getContentAsString();
        log.info("response: {}", response);
    }
}
