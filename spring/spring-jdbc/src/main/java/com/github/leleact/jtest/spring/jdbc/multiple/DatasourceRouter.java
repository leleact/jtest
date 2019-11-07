package com.github.leleact.jtest.spring.jdbc.multiple;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DatasourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }

}
