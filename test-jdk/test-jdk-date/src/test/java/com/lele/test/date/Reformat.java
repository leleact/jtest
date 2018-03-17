package com.lele.test.date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class Reformat {

    private static Logger log = LoggerFactory.getLogger(Reformat.class);

    @Test
    public void reformat() throws ParseException {
        String dateStr = "2017-06-17";

        SimpleDateFormat originSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = originSimpleDateFormat.parse(dateStr);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        System.out.println(sdf.format(date));
    }


    @Test
    public void parseTest() {
        String s = "2017-10-13 16:24:48";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(s);
            log.info(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("解析日期失败", e);
            date = new Date();
        }
    }

    @Test
    public void test1() {
      String d = "2017-01-21 05:42:23";
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(d);
            log.debug(date.toString());

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            log.debug("Year:[" + cal.get(Calendar.YEAR) + "], Month:[" + (cal.get(Calendar.MONTH) + 1)
                    + "], Day:[" + cal.get(Calendar.DATE) + "], Hour:[" + cal.get(Calendar.HOUR_OF_DAY)
                    + "], Min:["+ cal.get(Calendar.MINUTE)+"], Sec:["+ cal.get(Calendar.SECOND) +"]");

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            log.debug(sdf1.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Calendar cal = Calendar.getInstance();
        cal.set(2017, Calendar.NOVEMBER, 1, 15, 42, 23);
        Date d = cal.getTime();
        log.debug(d.toString());
    }
}