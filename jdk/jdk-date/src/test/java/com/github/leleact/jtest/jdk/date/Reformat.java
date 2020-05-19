package com.github.leleact.jtest.jdk.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class Reformat {

    private static Logger log = LoggerFactory.getLogger(Reformat.class);

    @Test
    void reformatTest() throws ParseException {
        String dateStr = "2017-06-17";
        SimpleDateFormat originSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = originSimpleDateFormat.parse(dateStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Assertions.assertEquals("20170617", sdf.format(date));
    }


    @Test
    void parseTest() throws ParseException {
        Assertions.assertDoesNotThrow(() -> {
            String s = "2017-10-13 16:24:48";
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sdf.parse(s);
            }
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(s);
                log.info("{}", date);
            }
            {
                Assertions.assertThrows(ParseException.class, () -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    Date date = sdf.parse(s);
                    log.info("{}", date);
                });
            }
        });
    }

    @Test
    void test1() throws ParseException {
        String d = "2017-01-21 05:42:23";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = sdf.parse(d);
        log.debug("{}", date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        log.debug("Year:[" + cal.get(Calendar.YEAR) + "], Month:[" + (cal.get(Calendar.MONTH) + 1)
                      + "], Day:[" + cal.get(Calendar.DATE) + "], Hour:[" + cal.get(Calendar.HOUR_OF_DAY)
                      + "], Min:[" + cal.get(Calendar.MINUTE) + "], Sec:[" + cal.get(Calendar.SECOND) + "]");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Assertions.assertEquals("20170121054223", sdf1.format(date));
    }

    @Test
    void test2() {
        Calendar cal = Calendar.getInstance();
        cal.set(2017, Calendar.NOVEMBER, 1, 15, 42, 23);
        Date d = cal.getTime();
        log.debug(d.toString());
    }
}
