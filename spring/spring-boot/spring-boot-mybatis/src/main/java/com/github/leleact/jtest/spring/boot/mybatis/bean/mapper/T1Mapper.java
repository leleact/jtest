package com.github.leleact.jtest.spring.boot.mybatis.bean.mapper;

import com.github.leleact.jtest.spring.boot.mybatis.bean.dto.T1;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Mapper
public interface T1Mapper {

    @InsertProvider(type = InsertProviderAdapter.class, method = "getSql")
    int insertSelective(T1 t1);

    @InsertProvider(type = InsertProviderAdapter.class, method = "insertBatch")
    int insertBatchSelective(List<T1> t1List);

    @Select("SELECT * FROM T1 where f1 = #{f1}")
    T1 selectByPrimaryKey(String f1);

    @SelectProvider(type = SelectProviderAdapter.class, method = "getSql")
    T1 selectBySelective(T1 t1);

    @Delete("DELETE FROM T1 WHERE F1 = #{t1.f1}")
    int delete(@Param("t1") T1 t1);

    @Delete("DELETE FROM T1")
    int deleteAll();


    class SelectProviderAdapter {
        public String getSql(T1 t1) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("T1");
                    if (t1.getF1() != null) {
                        WHERE("f1 = '" + t1.getF1() + "'");
                    }
                    if (t1.getF2() != null) {
                        WHERE("f2 = '" + t1.getF2() + "'");
                    }
                }
            }.toString();
        }
    }

    class InsertProviderAdapter {

        private static final Logger log = LoggerFactory.getLogger(InsertProviderAdapter.class);

        public String getSql(T1 t1) {
            return new SQL() {
                {
                    INSERT_INTO("T1");
                    if (t1.getF1() != null) {
                        VALUES("F1", "#{f1}");
                    }
                    if (t1.getF2() != null) {
                        VALUES("F2", "#{f2}");
                    }
                }
            }.toString();
        }

        public String insertBatch(Map<String, List<T1>> map) {

            List<T1> t1List = map.get("list");
            StringBuilder stringBuilder = new StringBuilder(256);
            stringBuilder.append("INSERT INTO T1 (F1, F2) VALUES ");
            MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].f1},#'{'list[{0}].f2})");
            for (int i = 0; i < t1List.size(); i++) {
                String si = "" + i;
                stringBuilder.append(messageFormat.format(new Object[]{si}));
                stringBuilder.append(",");
            }
            stringBuilder.setLength(stringBuilder.length() - 1);
            String sql = stringBuilder.toString();
            log.debug("sql : {}", sql);
            return sql;
        }
    }
}
