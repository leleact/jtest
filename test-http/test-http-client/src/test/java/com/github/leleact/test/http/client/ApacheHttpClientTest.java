package com.github.leleact.test.http.client;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ApacheHttpClientTest {

    private static final Logger log = LoggerFactory.getLogger(ApacheHttpClientTest.class);

    @Test
    public void test1() throws InterruptedException, ExecutionException, IOException {
        HttpClientContext context = HttpClientContext.create();
        HttpClientConnectionManager connMrg = new BasicHttpClientConnectionManager();
        HttpRoute route = new HttpRoute(new HttpHost("localhost", 80));
// Request new connection. This can be a long process
        ConnectionRequest connRequest = connMrg.requestConnection(route, null);
// Wait for connection up to 10 sec
        HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);
        try {
            // If not open
            if (!conn.isOpen()) {
                // establish connection based on its route info
                connMrg.connect(conn, route, 1000, context);
                // and mark it as route complete
                connMrg.routeComplete(conn, route, context);
            }
            // Do useful things with the connection.
        } finally {
            connMrg.releaseConnection(conn, null, 1, TimeUnit.MINUTES);
        }
    }

    @Test
    public void connPerRouteTest() {
        final String url = "http://localhost:8080/block";
        // 默认的httpClients最大20个链接，同一个route最多同时用2个
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpPost httpPost = null;
                    try {
                        httpPost = new HttpPost(url);
                        // 如果不设置requestTimeOut，当httpClient池中的资源用完之后，会一直等待 @see MainClientExec.java:191
                        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(6000).setSocketTimeout(60000).build();
                        httpPost.setConfig(requestConfig);
                        httpPost.setHeader("content-type", "application/json");

                        Map<String, String> requestMap = new HashMap<>();
                        requestMap.put("name", "嘿嘿");
                        httpPost.setEntity(new StringEntity(JSONObject.toJSONString(requestMap), StandardCharsets.UTF_8));
                        CloseableHttpResponse response1 = httpclient.execute(httpPost);
                        int status = response1.getStatusLine().getStatusCode();
                        if (status != 200) {
                            log.info("http 调用失败");
                        }
                        HttpEntity entity1 = response1.getEntity();
                        String result = IOUtils.toString(entity1.getContent(), StandardCharsets.UTF_8);
                        log.info("应答: {}", result);
                    } catch (IOException e) {
                        log.info("主机通讯异常", e);
                    } finally {
                        if (httpPost != null) {
                            httpPost.releaseConnection();
                        }
                    }
                }
            });
            threadList.add(t);
            t.start();
        }

        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void httpClientPostTest() {
        final PoolingHttpClientConnectionManager poolingmgr = new PoolingHttpClientConnectionManager();
        poolingmgr.setMaxTotal(2);
        poolingmgr.setDefaultMaxPerRoute(2);
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(
                6000).setSocketTimeout(60000).build();
        final CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(
                poolingmgr).setDefaultRequestConfig(requestConfig).build();


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int times = 10;
                int i = 0;
                while (i++ < times) {
                    log.info("======================第 {} 次 http请求开始 ============================", i);
                    HttpPost post = new HttpPost("http://localhost:8080/hello");
                    post.setEntity(new StringEntity("world", StandardCharsets.UTF_8));

                    HttpResponse response = null;
                    try {
                        response = httpclient.execute(post);
                    } catch (IOException e) {
                        log.info("", e);
                    }

                    if (response != null) {
                        try {
                            String s = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                            log.info(s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    log.info("======================第 {} 次 http请求结束 ============================", i);
                    try {
                        Thread.sleep(30000L * i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            log.info("", e);
        }


    }
}