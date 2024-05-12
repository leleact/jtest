package com.github.leleact.jtest.spring.jdbc.runner;

import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@Slf4j
public class JdbcUtil {
    public static void handlerResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            ResultSetMetaData m = rs.getMetaData();
            for (int i = 1; i <= m.getColumnCount(); i++) {
                log.info("rs columnName:{}, columnValue: {}", m.getColumnName(i), rs.getString(m.getColumnName(i)));
            }
        }
    }

    public static void handlerStatementResult(PreparedStatement stm) throws SQLException {
        ResultSet rs = stm.getResultSet();
        int count = stm.getUpdateCount();
        if (rs == null && count == -1) {
            log.info("No results found");
            return;
        }

        log.info("first output start");
        log.info("count: {}", count);
        if (rs != null) {
            handlerResultSet(rs);
        }
        log.info("first output end");

        while (stm.getMoreResults() || stm.getUpdateCount() != -1) {
            rs = stm.getResultSet();
            count = stm.getUpdateCount();
            log.info("next output start");
            log.info("count: {}", count);
            if (rs != null) {
                handlerResultSet(rs);
            }
            log.info("next output end");
        }
    }
}
