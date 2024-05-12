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
        HikariDataSource d1 = new HikariDataSource();
        d1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        d1.setUsername("root");
        d1.setPassword("root");
        d1.setJdbcUrl("jdbc:mysql://10.0.1.3:3306/test");
        d1.setSchema("test");
        return d1;
    }

    @Bean
    public DataSource d2() {
        HikariDataSource d2 = new HikariDataSource();
        d2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        d2.setUsername("root");
        d2.setPassword("root");
        d2.setJdbcUrl("jdbc:mysql://10.0.1.3:3306/test");
        d2.setSchema("test");
        return d2;
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
