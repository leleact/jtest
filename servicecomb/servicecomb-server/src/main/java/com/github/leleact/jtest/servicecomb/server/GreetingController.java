package com.github.leleact.jtest.servicecomb.server;

import com.github.leleact.jtest.servicecomb.server.model.GreetingRequest;
import com.github.leleact.jtest.servicecomb.server.model.GreetingResponse;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * greeting controller
 *
 * @author leleact
 * @since 2024-03-16
 */
@RestSchema(schemaId = "Greeting")
@RestController
@RequestMapping(value = "/greet", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class GreetingController {

    @PostMapping("/hello")
    public GreetingResponse greet(@RequestBody @Valid GreetingRequest request) {
        GreetingResponse response = new GreetingResponse();
        response.setWord("hello, " + request.getName());
        return response;
    }
}
