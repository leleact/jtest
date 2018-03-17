package com.lele.test.testspringbootpropertysource.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:lele.properties",
        "classpath:datasource/datasource.properties"}, encoding = "UTF-8")
public class LeleConfig {

//    @ConfigurationProperties(prefix = "datasource")
    @Bean
    public DataSourceConfig getDatasourceConfig() {
        return new DataSourceConfig();
    }
}
