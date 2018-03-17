package com.lele.test.http.client;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.core.util.ExecutorServices;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * a very simple http client upload file example, no exception catch and resource close
 */
public class ApacheHttpClientUploadFile {

    private static final Logger log = LoggerFactory.getLogger(ApacheHttpClientUploadFile.class);

    @Test
    public void upLoadFileTest() {

        HttpClientConnectionManager hcmg = new PoolingHttpClientConnectionManager();

        final CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(hcmg).build();

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<String> c = new Callable<String>() {
                @Override
                public String call() {
                    try {
                        HttpPost httpPost = new HttpPost("http://localhost/file/upload/gateway");

                        String pathname = "C:\\Users\\vacp\\Desktop\\1.jpg";

//        HttpEntity httpEntity = MultipartEntityBuilder.create().addBinaryBody("file", new File(pathname)).build();

                        File f = new File(pathname);
                        FileInputStream inputStream = FileUtils.openInputStream(f);
                        MultipartEntity httpEntity = new MultipartEntity();
                        httpEntity.addPart("file", new InputStreamBody(inputStream, f.getName()));

                        //MultipartEntityBuilder.create().addPart("111", new FileBody( new File("")));

                        httpPost.setEntity(httpEntity);

                        HttpResponse response = httpClient.execute(httpPost);
                        String ret = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                        log.info("服务器应答: {}", ret);
                        return ret;
                    } catch (Exception e) {
                        log.error("", e);
                    }
                    return "ok";
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
