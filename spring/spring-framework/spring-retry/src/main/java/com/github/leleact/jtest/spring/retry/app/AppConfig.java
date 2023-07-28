package com.github.leleact.jtest.spring.retry.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;

/**
 * app config
 *
 * @author leleact
 * @since 2023-07-28
 */
@Configuration
@EnableRetry
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
