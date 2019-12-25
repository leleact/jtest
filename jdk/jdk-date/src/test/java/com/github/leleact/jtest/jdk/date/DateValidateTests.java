package com.github.leleact.jtest.jdk.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateValidateTests {

    @Test
    public void validateDateTest() throws ParseException {
        String date = "20190230";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date d = sdf.parse(date);
        log.info("d=[{}]", d.getTime());
        Assertions.assertNotNull(d);
    }
}
