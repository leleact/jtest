package com.github.leleact.jtest.spring.cloud.feign.api.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * message header.
 *
 * @author leleact
 */
@ApiModel("报文头")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageHeader {

    private String version;

    private String name;
}
