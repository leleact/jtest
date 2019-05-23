package com.github.leleact.jtest.apache.http.client.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
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
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
class ApacheHttpClientTest {

    @Test
    void test1() throws InterruptedException, ExecutionException, IOException {
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
    void connPerRouteTest() {
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
                        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(60000).setSocketTimeout(60000).build();
                        httpPost.setConfig(requestConfig);
                        httpPost.setHeader("content-type", "application/json");

                        httpPost.setEntity(new StringEntity("{\"name\":\"xxx\"}", StandardCharsets.UTF_8));
                        String response = httpclient.execute(httpPost, new ResponseHandler<String>() {
                            @Override
                            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                                return  EntityUtils.toString(httpResponse.getEntity());
                            }
                        });
                        log.info("response: {}", response);
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
    void httpClientPostTest() {
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

                    try {
                        String response = httpclient.execute(post, new ResponseHandler<String>() {
                            @Override
                            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                                return EntityUtils.toString(httpResponse.getEntity());
                            }
                        });
                    } catch (IOException e) {
                        log.warn(e.getMessage(), e);
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
