package com.github.leleact.test.http.client;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class ApacheHttpClientProxyTests {

    @Test
    public void proxyTest() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpClient httpclient = HttpClientBuilder.create()
                .setSSLContext(SSLContexts.custom().loadTrustMaterial(TrustAllStrategy.INSTANCE).build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

        try {

            HttpHost target = new HttpHost("www.google.com", 443, "https");
            HttpHost proxy = new HttpHost("127.0.0.1", 8087, "http");

            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            HttpGet request = new HttpGet("/");
            request.setConfig(config);

            System.out.println("Executing request " + request.getRequestLine() + " to " + target + " via " + proxy);

            String response = httpclient.execute(target, request,
                    new ResponseHandler<String>() {
                        @Override
                        public String handleResponse(HttpResponse response) throws
                                ClientProtocolException, IOException {
                            return EntityUtils.toString(response.getEntity());
                        }
                    });
            System.out.println("----------------------------------------");
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
