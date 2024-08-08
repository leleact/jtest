package com.github.leleact.jtest.mybatis.spring.type.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JsonTypeHandler.
 *
 * @author leleact
 * @since 8/6/2024
 */
@Slf4j
@MappedTypes({JsonNode.class})
public class JsonTypeHandler extends BaseTypeHandler<JsonNode> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonNode parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new TypeHandlerException("set parameter error", e);
        }
    }

    @Override
    public JsonNode getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (null != value) {
            try {
                return objectMapper.readTree(value);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new TypeHandlerException("get result by columnName error", e);
            }
        }
        return null;
    }

    @Override
    public JsonNode getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (null != value) {
            try {
                return objectMapper.readTree(value);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new TypeHandlerException("get result by columnIndex error", e);
            }
        }
        return null;
    }

    @Override
    public JsonNode getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (null != value) {
            try {
                return objectMapper.readTree(value);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new TypeHandlerException("get result by columnIndex from CallableStatement error", e);
            }
        }
        return null;
    }
}
