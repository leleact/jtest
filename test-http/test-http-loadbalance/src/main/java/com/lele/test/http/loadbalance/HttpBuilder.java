package com.lele.test.http.loadbalance;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HttpBuilder {

    public static List<HttpGet> buildHttpGet(URI... uris) {
        List<HttpGet> list = new ArrayList<>();
        for (URI uri : uris)
            list.add(new HttpGet(uri));
        return list;
    }

    public static List<HttpGet> buildHttpGet(String... uris) {
        List<HttpGet> list = new ArrayList<>();
        for (String uri : uris)
            list.add(new HttpGet(uri));
        return list;
    }

    public static List<HttpPost> buildHttpPost(URI... uris) {
        List<HttpPost> list = new ArrayList<>();
        for (URI uri : uris)
            list.add(new HttpPost(uri));
        return list;
    }

    public static List<HttpPost> buildHttpPost(String... uris) {
        List<HttpPost> list = new ArrayList<>();
        for (String uri : uris)
            list.add(new HttpPost(uri));
        return list;
    }
}
