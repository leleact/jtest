package com.lele.test.http.old.client;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class HttpOldClientTests {

    private static final Logger log = LoggerFactory.getLogger(HttpOldClientTests.class);

    private static SSLContext sslContext;

    static {
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs,
                        String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs,
                        String authType) {
                }
            }}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            log.error("init SSLContext error, No Such Algorithm for SSLContext", e);
        } catch (KeyManagementException e) {
            log.error("init SSLContext error, KeyManagement fail", e);
        }
    }

    @Test
    public void httpClientTest() throws IOException {
        HttpParams httpParams = new BasicHttpParams();
        httpParams.setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 10000);
        httpParams.setParameter(HttpConnectionParams.SO_TIMEOUT, 10000);
        httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, HTTP.UTF_8);

        SSLSocketFactory sf = new SSLSocketFactory(sslContext);
        Scheme httpsScheme = new Scheme("https", sf, 443);
        Scheme httpScheme = new Scheme("http", sf, 80);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(httpsScheme);
        schemeRegistry.register(httpScheme);

        ClientConnectionManager cm = new SingleClientConnManager(httpParams, schemeRegistry);
        HttpClient httpClient = new DefaultHttpClient(cm, httpParams);

        HttpPost httpPost = new HttpPost("https://www.baidu.com:443");
        List<NameValuePair> values = new ArrayList<>();

        values.add(new BasicNameValuePair("q", "嘿嘿"));

        httpPost.setEntity(new UrlEncodedFormEntity(values, "UTF-8"));

        HttpResponse httpResponse = httpClient.execute(httpPost);

        String responseStr = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        log.info("{}", responseStr);
    }



    @Test
    public void httpClientDefaultTest() {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {
            SSLSocketFactory sf = new SSLSocketFactory(sslContext);
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sf, 443));
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("http", sf, 80));

            HttpParams httpParams = new BasicHttpParams();
            httpParams.setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 10000);
            httpParams.setParameter(HttpConnectionParams.SO_TIMEOUT, 10000);
            httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, HTTP.UTF_8);

            HttpPost httpPost = new HttpPost("https://www.baidu.com:443");
            httpPost.setParams(httpParams);
            List<NameValuePair> values = new ArrayList<>();
            values.add(new BasicNameValuePair("q", "嘿嘿"));

            httpPost.setEntity(new UrlEncodedFormEntity(values, "UTF-8"));

            HttpResponse httpResponse = httpClient.execute(httpPost);

            String responseStr = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

            log.info("{}", responseStr);
        } catch (IOException e) {
            // log
        } finally {
            if (httpClient.getConnectionManager() != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
    }
}
