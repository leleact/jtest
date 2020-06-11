package com.github.leleact.jtest.spring.cloud.feign.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hierarchy request
 *
 * @author leleact
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HierarchyRequest {

    private MessageHeader header;
}
