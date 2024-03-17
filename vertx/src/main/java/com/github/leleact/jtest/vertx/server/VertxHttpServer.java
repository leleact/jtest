package com.github.leleact.jtest.vertx.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VertxHttpServer extends AbstractVerticle {

    private static final Logger log = LoggerFactory.getLogger(VertxHttpServer.class);

    @Override
    public void start(Promise<Void> future) {
        Router router = Router.router(vertx);
        router.getRoutes();
        router.route().handler(routingContext -> {
            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");
            // Write to the response and end it
            response.end("Hello World from Vert.x-Web!");
        });

        vertx.createHttpServer().requestHandler(router).listen(9090, result -> {
            if (result.succeeded()) {
                log.info("server start success!");
                future.complete();
            } else {
                future.fail(result.cause());
            }
        });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new VertxHttpServer());
    }
}
