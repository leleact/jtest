package com.github.leleact.jtest.spring.boot.context;

import com.github.leleact.jtest.spring.boot.context.bean.EchoService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class MockEchoService implements EchoService {
    @Override
    public String echo(String str) {
        return "mock " + str;
    }
}
