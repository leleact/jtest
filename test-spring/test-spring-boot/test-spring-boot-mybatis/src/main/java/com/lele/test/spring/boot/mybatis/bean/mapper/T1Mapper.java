package com.lele.test.spring.boot.mybatis.bean.mapper;

import com.lele.test.spring.boot.mybatis.bean.dto.T1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface T1Mapper {

    @Select("SELECT * FROM T1 where f1 = #{f1}")
    T1 selectByPrimaryKey(String f1);

    @SelectProvider(type = SelectProviderAdapter.class, method = "getSql")
    T1 selectBySelective(T1 t1);


    public static class SelectProviderAdapter {
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
}
