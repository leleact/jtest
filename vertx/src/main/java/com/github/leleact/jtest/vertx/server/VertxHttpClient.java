package com.github.leleact.jtest.vertx.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VertxHttpClient extends AbstractVerticle {

    private final static Logger log = LoggerFactory.getLogger(VertxHttpClient.class);

    @Override
    public void start() {
        HttpClient client = vertx.createHttpClient();
        client.request(HttpMethod.GET, 9090, "localhost", "/").compose(req -> req.send().compose(resp -> {
            log.info("Got response {}", resp.statusCode());
            return resp.body();
        })).onSuccess(body -> {
            log.info("Got data {}", body.toString("ISO-8859-1"));
            vertx.close();
        }).onFailure(Throwable::printStackTrace);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new VertxHttpClient());
    }
}
