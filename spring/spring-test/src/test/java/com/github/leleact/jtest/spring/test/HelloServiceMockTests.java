package com.github.leleact.jtest.spring.test;

import com.github.leleact.jtest.spring.test.repository.HelloRepository;
import com.github.leleact.jtest.spring.test.service.HelloService;
import com.github.leleact.jtest.spring.test.service.impl.HelloServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

/**
 * hello service mock tests.
 *
 * @author leleact
 * @since 1.0
 */
@SpringBootTest
class HelloServiceMockTests {

    @Mock
    private HelloRepository helloRepository;

    @InjectMocks
    private HelloService helloService = new HelloServiceImpl();

    @BeforeEach
    void setMockOutput() {
        when(helloRepository.getPrefix()).thenReturn("mock");
    }

    @DisplayName("Test Mock helloService + helloRepository")
    @Test
    void helloTest() {
        Assertions.assertEquals("real mock xxx", helloService.hello("xxx"));
    }
}
