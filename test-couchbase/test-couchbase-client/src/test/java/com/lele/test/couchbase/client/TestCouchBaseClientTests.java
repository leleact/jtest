package com.lele.test.couchbase.client;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.Document;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.transcoder.Transcoder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TestCouchBaseClientTests {

    private static final Logger log = LoggerFactory.getLogger(TestCouchBaseClientTests.class);

    @Test
    public void connectClusterTest() {

        final String KEY = "???";

        CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder()
                .connectTimeout(100000).build();
        Cluster cluster = CouchbaseCluster.create(env, "???,???,???");

        List<Transcoder<? extends Document, ?>> transcoders = new ArrayList<>();

        Bucket bucket = cluster.openBucket("???", "???", transcoders);

        boolean isExists = bucket.exists(KEY);
        log.info("{}", isExists);
        log.info("{}", bucket.get(KEY));
        bucket.close();
    }

}
