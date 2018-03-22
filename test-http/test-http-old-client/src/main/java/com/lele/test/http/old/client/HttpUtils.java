package com.lele.test.http.old.client;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
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
import java.util.Map;

public class HttpUtils {

    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);

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


    public String httpPost(String url, Map<String, String> params, int connectionTimeout, int soTimeout,
            String charset) {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {
            SSLSocketFactory sf = new SSLSocketFactory(sslContext);
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sf, 443));
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("http", sf, 80));

            HttpParams httpParams = new BasicHttpParams();
            httpParams.setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 10000);
            httpParams.setParameter(HttpConnectionParams.SO_TIMEOUT, 10000);
            httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, charset);

            HttpPost httpPost = new HttpPost(url);
            httpPost.setParams(httpParams);

            List<NameValuePair> values = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                values.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(values, charset));
            HttpResponse httpResponse = httpClient.execute(httpPost);

            String responseStr = EntityUtils.toString(httpResponse.getEntity(), charset);
            log.info("{}", responseStr);

            return responseStr;
        } catch (IOException e) {
            log.error("IOException", e);
        } finally {
            if (httpClient.getConnectionManager() != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        return null;
    }
}
