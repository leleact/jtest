package com.github.leleact.jtest.mapstruct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * mapstruct test
 *
 * @author leleact
 * @since 2022-03-14
 */
public class MapStructTests {
    public static class Pojo {
        private String name;
        private Integer age;
        private String date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }


    public static class PojoDto {
        private String name;
        private Integer age;
        private Date date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    @Mapper
    public interface PojoMapper {
        PojoMapper INSTANCE = Mappers.getMapper(PojoMapper.class);

        @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd HH:mm:ss")
        PojoDto toDto(Pojo pojo);
    }

    @Test
    public void mapStructCopyPojoTest() {
        Pojo pojo = new Pojo();
        pojo.setName("leleact");
        pojo.setAge(18);
        pojo.setDate("2020-03-14 14:00:00");
        PojoDto pojoDto = PojoMapper.INSTANCE.toDto(pojo);
        Assertions.assertEquals("leleact", pojoDto.getName());
        Assertions.assertEquals(18, pojoDto.getAge());
        Assertions.assertEquals("2020-03-14 14:00:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pojoDto.getDate()));
    }
}
