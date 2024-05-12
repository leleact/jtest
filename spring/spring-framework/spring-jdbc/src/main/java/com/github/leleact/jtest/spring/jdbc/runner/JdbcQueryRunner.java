package com.github.leleact.jtest.spring.jdbc.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Slf4j
@Component
public class JdbcQueryRunner implements CommandLineRunner {

    @Autowired
    @Qualifier("routingDataSource")
    private DataSource dataSource;

    @Override
    public void run(String... args) {
        log.info("jdbc query runner start");
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement("SELECT * FROM person WHERE id = ?")) {
                stm.setInt(1, 1);
                stm.execute();
                JdbcUtil.handlerStatementResult(stm);
            } catch (Throwable e) {
                log.error(e.getMessage(), e);
            }
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
        log.info("jdbc query runner end");
    }
}
