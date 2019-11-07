package com.github.leleact.jtest.spring.jdbc.multiple;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {


    @Bean
    public DataSource d1() {
        return new HikariDataSource();
    }

    @Bean
    public DataSource d2() {
        return new HikariDataSource();
    }

    @Bean
    public DataSource routingDataSource() {
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("a", d1());
        dataSources.put("b", d2());
        DatasourceRouter router = new DatasourceRouter();
        router.setTargetDataSources(dataSources);
        router.setDefaultTargetDataSource(d1());
        return router;
    }
}
