package com.lele.test.spring.mvc.test;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring-mvc.xml"}, loader = WebDelegatingSmartContextLoader.class)
@WebAppConfiguration
public class PropertiesControllerTests {

    private static final Logger log = LoggerFactory.getLogger(PropertiesControllerTests.class);

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void propClassLoaderTest() throws Exception {
        String response = this.mockMvc.perform(
                get("/prop/classloader").contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"a\":\"1\",\"b\":\"2\"}"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(response);
    }

    @Test
    public void propClassTest() throws Exception {
        String response = this.mockMvc.perform(
                get("/prop/class").contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"a\":\"1\",\"b\":\"2\"}"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(response);
    }
}
