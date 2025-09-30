package com.github.leleact.jtest.spring.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.tools.agent.ReactorDebugAgent;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * webclient test.
 *
 * @author leleact
 * @since 2025-09-08
 */
@Slf4j
public class RealWebClientTests {
    @Disabled
    @Test
    public void realGetGoogleSearchTest() throws InterruptedException {
        ReactorDebugAgent.init();
        WebClient client = WebClient.builder().baseUrl("https://www.google.com").build();
        Mono<ClientResponse> responseMono = client.get().uri("/").exchangeToMono(clientResponse -> {
            if (clientResponse.statusCode().is2xxSuccessful()) {
                log.info("return response mono");
                return Mono.just(clientResponse);
            } else {
                return clientResponse.createError();
            }
        });
        try {
            log.info("1");
            responseMono.subscribe(new Consumer<ClientResponse>() {
                @Override
                public void accept(ClientResponse clientResponse) {
                    log.info("Status code: {}", clientResponse.statusCode());
                    clientResponse.body(new BodyExtractor<String, ClientHttpResponse>() {
                        @Override
                        public String extract(ClientHttpResponse inputMessage, Context context) {
                            log.info("xxxx");
                            inputMessage.getBody().subscribe(new Consumer<DataBuffer>() {
                                @Override
                                public void accept(DataBuffer dataBuffer) {
                                    log.info("yyy");
                                    log.info(dataBuffer.toString(StandardCharsets.UTF_8));
                                }
                            });
                            return "";
                        }
                    });
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
