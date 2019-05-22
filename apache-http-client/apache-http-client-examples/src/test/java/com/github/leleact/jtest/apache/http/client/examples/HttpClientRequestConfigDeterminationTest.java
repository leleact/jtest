package com.github.leleact.jtest.apache.http.client.examples;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

class HttpClientRequestConfigDeterminationTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientRequestConfigDeterminationTest.class);

    private static CloseableHttpClient httpClient;

    static {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(
                10000).setConnectTimeout(
                10000).setSocketTimeout(
                10000).build();
        httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    }

    @Test
    void postTest() throws IOException {
        HttpPost post = new HttpPost("https://www.baidu.com");
        post.setConfig(
                RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(50000).setSocketTimeout(
                        5000).build());
        CloseableHttpResponse response = httpClient.execute(post);
        String respStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        logger.info("===>{}", respStr);
    }
}
