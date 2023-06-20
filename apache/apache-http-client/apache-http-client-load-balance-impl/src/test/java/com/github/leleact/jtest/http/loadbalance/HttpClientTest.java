package com.github.leleact.jtest.http.loadbalance;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Disabled
@Slf4j
class HttpClientTest {

    @Test
    void test1() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new RandomLoadBalance<>(HttpBuilder.buildHttpGet("xxx", "yyyy")).selectItem();
        CloseableHttpResponse response = httpClient.execute(httpget);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                long len = entity.getContentLength();
                if (len != -1 && len < 2048) {
                    System.out.println(EntityUtils.toString(entity));
                } else {
                    // Stream content out
                }
            }
        } finally {
            response.close();
        }
    }
}
