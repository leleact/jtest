package com.github.leleact.jtest.spring.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * MockRestServiceServer test
 *
 * @author leleact
 * @since 2022-04-26
 */
@SpringBootTest
public class MockRestServiceServerTests {
    @Test
    public void springWebMockServerTest() {
        RestTemplate restTemplate = new RestTemplate();
        MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
        server.expect(request -> Assertions.assertTrue(request.getURI().getPath().contains("/test"))).andRespond(request -> new MockClientHttpResponse("ok".getBytes(StandardCharsets.UTF_8), HttpStatus.OK));
        String response = restTemplate.postForObject("http://localhost:8080/test", "", String.class);
        Assertions.assertEquals("ok", response);
    }
}
