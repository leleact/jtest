package com.lele.test.http.client;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
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

                            String pathname = "C:\\Users\\vacp\\Desktop\\1.jpg";

//        HttpEntity httpEntity = MultipartEntityBuilder.create().addBinaryBody("file", new File(pathname)).build();

                            File f = new File(pathname);
                            ByteArrayInputStream inputStream = new ByteArrayInputStream(
                                    FileUtils.readFileToByteArray(f));

                            MultipartEntity httpEntity = new MultipartEntity();
                            httpEntity.addPart("file", new InputStreamBody(inputStream, f.getName()));
                            log.info("===================={}=======================", httpEntity.isRepeatable());

                            //MultipartEntityBuilder.create().addPart("111", new FileBody( new File("")));

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
