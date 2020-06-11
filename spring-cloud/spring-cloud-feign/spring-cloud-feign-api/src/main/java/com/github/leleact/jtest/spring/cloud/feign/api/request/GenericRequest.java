package com.github.leleact.jtest.spring.cloud.feign.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * generic type request.
 *
 * @param <T> send request
 * @author leleact
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericRequest<T> {

    private MessageHeader header;

    private T info;
}
