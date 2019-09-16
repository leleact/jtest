package com.github.leleact.jtest.spring.boot.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBindTester {

    private static final Logger log = LoggerFactory.getLogger(DataBindTester.class);

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

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
