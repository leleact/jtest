package com.lele.test.spring.boot.mybatis.bean.mapper;

import com.lele.test.spring.boot.mybatis.bean.dto.T1;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface T1Mapper {

    @InsertProvider(type = InsertProviderAdapter.class, method = "getSql")
    int insertSelective(T1 t1);

    @Select("SELECT * FROM T1 where f1 = #{f1}")
    T1 selectByPrimaryKey(String f1);

    @SelectProvider(type = SelectProviderAdapter.class, method = "getSql")
    T1 selectBySelective(T1 t1);

    @Delete("DELETE FROM T1 WHERE F1 = #{t1.f1}")
    int delete(@Param("t1") T1 t1);


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
        public String getSql(T1 t1) {
            return new SQL() {
                {
                    INSERT_INTO("T1");
                    if (t1.getF1() != null) {
                        INTO_COLUMNS("f1");
                    }
                    if (t1.getF2() != null) {
                        INTO_COLUMNS("f2");
                    }
                    if (t1.getF1() != null) {
                        INTO_VALUES(t1.getF1());
                    }
                    if (t1.getF2() != null) {
                        INTO_VALUES(t1.getF2());
                    }
                }
            }.toString();
        }
    }
}
