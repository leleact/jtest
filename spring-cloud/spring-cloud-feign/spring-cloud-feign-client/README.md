# feign client

### feign-client properties
org.springframework.cloud.openfeign.FeignClientProperties#Map<String, FeignClientConfiguration>

### ribbon loadbalancer
ribbon loadbalancer in maintain mode, so suggest `org.springframework.cloud:spring-cloud-loadbalancer` and set `spring.cloud.loadbalancer.ribbon.enabled=false`

#### when use ribbon loadbalancer

set feign configuration should set ribbon configuration