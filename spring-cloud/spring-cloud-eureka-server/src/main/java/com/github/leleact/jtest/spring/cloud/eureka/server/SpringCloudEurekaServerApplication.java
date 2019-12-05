package com.github.leleact.jtest.spring.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaServerApplication {

//    @Bean
//    Filter httpTraceFilter() {
//        return new Filter() {
//            @Override
//            public void init(FilterConfig filterConfig) throws ServletException {
//
//            }
//
//            @Override
//            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//                filterChain.doFilter(servletRequest, servletResponse);
//            }
//
//            @Override
//            public void destroy() {
//
//            }
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaServerApplication.class, args);
    }
}
