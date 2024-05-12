package com.github.leleact.jtest.spring.jdbc.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;

@Slf4j
@Component
public class JdbcCallProcedureRunner implements CommandLineRunner {

    @Autowired
    @Qualifier("routingDataSource")
    private DataSource dataSource;

    @Override
    public void run(String... args) {
        log.info("jdbc call procedure runner start");
        try (Connection conn = dataSource.getConnection()) {
            try (CallableStatement stm = conn.prepareCall("call resultSetDemo()")) {
                stm.execute();
                JdbcUtil.handlerStatementResult(stm);
            } catch (Throwable e) {
                log.error(e.getMessage(), e);
            }
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
        log.info("jdbc call procedure runner end");
    }
}
