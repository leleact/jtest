package com.github.leleact.jtest.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.core.net.SocketAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * connection pool test
 *
 * @author leleact
 * @since 2024-03-06
 */

public class ConnectionPoolTests {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPoolTests.class);

    private static final String DEFAULT_HOST = "localhost";

    private static final int DEFAULT_PORT = 48080;

    private void roundHandler(Vertx vertx, AtomicInteger round, HttpClient client, CountDownLatch latch, int requestCount, long intervalMs, AtomicLong msAdjuster) {
        LOG.info("Round {} begin", round.incrementAndGet());
        AtomicInteger remainingResponses = new AtomicInteger(requestCount);
        for (int i = 0; i < requestCount; i++) {
            client.request(HttpMethod.GET, DEFAULT_PORT, DEFAULT_HOST, String.format("test?id=%d.%d", round.get(), i))
                  .compose(HttpClientRequest::send)
                  .onComplete(event -> {
                      HttpClientResponse resp = event.result();
                      Assertions.assertEquals(200, resp.statusCode());
                      LOG.debug("Received response for {}", resp.request().getURI());
                      if (remainingResponses.decrementAndGet() == 0) {
                          latch.countDown();
                          if (latch.getCount() > 0) {
                              long delayTime = intervalMs + msAdjuster.get() + 1000 - System.currentTimeMillis() % 1000;
                              vertx.setTimer(delayTime,
                                  l -> roundHandler(vertx, round, client, latch, requestCount, intervalMs, msAdjuster));
                          }
                      }
                  });
        }
    }

    @Test
    public void vertxConnectionPoolTest() throws InterruptedException {
        AtomicLong msAdjuster = new AtomicLong(1000);

        // a hack to get the timestamp when keep-alive timeout is checked
        //        Consumer<Endpoint<Lease<HttpClientConnection>>> oldChecker = HttpClientImpl.EXPIRED_CHECKER;
        //        HttpClientImpl.EXPIRED_CHECKER = endPoint -> {
        //            LOG.debug("Expired checker called");
        //            msAdjuster.set(System.currentTimeMillis() % 1000);
        //            oldChecker.accept(endPoint);
        //        };

        Vertx vertx = Vertx.vertx();
        final int keepAliveTimeout = 1;
        HttpClient client = vertx.createHttpClient(new HttpClientOptions().setKeepAlive(true)
                                                                          .setKeepAliveTimeout(keepAliveTimeout)
                                                                          .setPipelining(false)
                                                                          .setMaxPoolSize(2)
                                                                          .setPoolCleanerPeriod(1000));

        HttpServer server = vertx.createHttpServer(new HttpServerOptions().setHost(DEFAULT_HOST).setPort(DEFAULT_PORT));
        server.requestHandler(req -> {
            LOG.debug("Received request {}", req.getParam("id"));
            req.response().end();
        });

        CountDownLatch startServerLatch = new CountDownLatch(1);

        server.listen(SocketAddress.inetSocketAddress(DEFAULT_PORT, DEFAULT_HOST),
            new Handler<AsyncResult<HttpServer>>() {
                @Override
                public void handle(AsyncResult<HttpServer> event) {
                    startServerLatch.countDown();
                }
            });
        startServerLatch.await();
        LOG.info("Started server");

        // run 100 rounds
        AtomicInteger round = new AtomicInteger(0);
        CountDownLatch roundLatch = new CountDownLatch(100);
        vertx.runOnContext(v -> roundHandler(vertx, round, client, roundLatch, 5, keepAliveTimeout * 1000, msAdjuster));

        roundLatch.await(10L, TimeUnit.MINUTES);

        LOG.info("Closing server");
        CountDownLatch serverCloseLatch = new CountDownLatch(1);
        server.close(new Handler<AsyncResult<Void>>() {
            @Override
            public void handle(AsyncResult<Void> event) {
                serverCloseLatch.countDown();
            }
        });
        serverCloseLatch.await();

        //   HttpClientImpl.EXPIRED_CHECKER = oldChecker;
    }
}
