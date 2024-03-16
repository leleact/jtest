package com.github.leleact.jtest.servicecomb.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.leleact.jtest.servicecomb.client.model.GreetingRequest;
import com.github.leleact.jtest.servicecomb.client.model.GreetingResponse;
import org.apache.servicecomb.core.BootListener;
import org.apache.servicecomb.provider.springmvc.reference.async.CseAsyncRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.*;

/**
 * boot listener
 *
 * @author leleact
 * @since 2024-03-16
 */
@Component
public class ClientBootListener implements BootListener {
    private static final Logger log = LoggerFactory.getLogger(ClientBootListener.class);

    private final CseAsyncRestTemplate template = new CseAsyncRestTemplate();

    private final ObjectMapper OM = new ObjectMapper();

    private ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(1);

    @Override
    public void onAfterRegistry(BootEvent event) {
        schedule.schedule(this::sendRequest, 5, TimeUnit.SECONDS);
    }

    private void sendRequest() {
        GreetingRequest request = new GreetingRequest();
        request.setName("abc");
        request.setAge(1);
        request.setEmail("a@b.com");
        HttpEntity<GreetingRequest> entity = new HttpEntity<>(request);

        ListenableFuture<ResponseEntity<GreetingResponse>> future = template.postForEntity(
            "servicecomb://server/greet/hello", entity, GreetingResponse.class);
        try {
            ResponseEntity<GreetingResponse> resEntity = future.get(10L, TimeUnit.SECONDS);
            GreetingResponse response = OM.readValue(OM.writeValueAsString(resEntity.getBody()),
                GreetingResponse.class);
            log.info("response: {}", response);
        } catch (InterruptedException | ExecutionException | TimeoutException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
