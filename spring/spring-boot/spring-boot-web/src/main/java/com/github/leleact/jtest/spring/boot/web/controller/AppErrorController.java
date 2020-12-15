package com.github.leleact.jtest.spring.boot.web.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * ErrorController interface Deprecated since 2.3.0.
 */
@RestController
@ConditionalOnProperty(prefix = "web.error", name = "enabled", havingValue = "true")
public class AppErrorController /* implements ErrorController */ {

    private ErrorAttributes errorAttributes;

    public AppErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @GetMapping("/error")
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request);
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }


    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        ServletWebRequest requestAttributes = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.BINDING_ERRORS));
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
            .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /*
    @Override
    public String getErrorPath() {
        return "/error";
    }
    */
}
