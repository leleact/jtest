package com.github.leleact.jtest.apache.http.client.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * a very simple http client upload file example, no exception catch and resource close
 */

@Slf4j
@Disabled
class ApacheHttpClientUploadFile {

    @Test
    void upLoadFileTest() {

        HttpClientConnectionManager hcmg = new PoolingHttpClientConnectionManager();

        final CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(hcmg).build();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int j = 0; j < 10; j++) {
            try {
                Thread.sleep(5000L * j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Future<String>> l = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Callable<String> c = new Callable<String>() {
                    @Override
                    public String call() {
                        try {
                            HttpPost httpPost = new HttpPost("http://localhost:8080/file/upload/gateway");
                            InputStream inputStream= this.getClass().getResourceAsStream("/uploadFile.txt");
                            MultipartEntity httpEntity = new MultipartEntity();
                            httpEntity.addPart("file", new InputStreamBody(inputStream, "uploadFile.txt"));
                            log.info("===================={}=======================", httpEntity.isRepeatable());
                            httpPost.setEntity(httpEntity);
                            HttpResponse response = httpClient.execute(httpPost);
                            String ret = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                            log.info("服务器应答: {}", ret);
                            return ret;
                        } catch (Exception e) {
                            log.error("", e);
                        }
                        return "fail";
                    }
                };
                Future<String> f = executorService.submit(c);
                l.add(f);
            }
            for (Future<String> f : l) {
                try {
                    String r = f.get(10L, TimeUnit.SECONDS);
                    log.info(r);
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    log.info("", e);
                }
            }
        }
    }
}
