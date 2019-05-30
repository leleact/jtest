package com.lele.test.jdk.pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {

    private static Logger log = LoggerFactory.getLogger(TestMain.class);

    @Test
    public void test1() throws Exception {
        String str = "91234567890";

        // 先匹配一个1-9的数字，然后匹配10次0-9的数字
        Pattern pattern = Pattern.compile("^[1-9][0-9]{10}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            log.debug("matched");
        } else {
            log.debug("unmatched");
        }
    }


    @Test
    public void test2() {
        {
            Pattern pattern = Pattern.compile("");

            Matcher matcher = pattern.matcher("");

            if (matcher.matches()) {
                log.debug("matched");
            } else {
                log.debug("unmatched");
            }
        }


        {
            String patternStr = "\\w{1,}";
            log.info("Pattern String [" + patternStr + "]");
            Pattern pattern = Pattern.compile(patternStr);


            String str = "aaabbbccc";

            Matcher matcher = pattern.matcher(str);
            if (matcher.matches()) {
                log.info("matched");
            } else {
                log.info("unmatched");
            }

        }
    }

    @Test
    public void performanceTest() {
        String str = "91234567890";
        int times = 1000000;

        {
            long start = System.currentTimeMillis();
            while (times-- > 0) {
                Pattern pattern = Pattern.compile("^[1-9][0-9]{10}");
                Matcher matcher = pattern.matcher(str);
            }
            long end = System.currentTimeMillis();
            log.info("" + (end - start) + "ms");
        }

        {
            long start = System.currentTimeMillis();
            while (times-- > 0) {
                boolean ret = str.equals("91234567890");
            }
            long end = System.currentTimeMillis();
            log.info("" + (end - start) + "ms");
        }

    }


    public static class Param {

        private String ch_id;

        private String acc_type;

        private String prd_id;

        public Param() {
        }

        public Param(String ch_id, String acc_type, String prd_id) {
            this.ch_id = ch_id;
            this.acc_type = acc_type;
            this.prd_id = prd_id;
        }

        public String getCh_id() {
            return ch_id;
        }

        public void setCh_id(String ch_id) {
            this.ch_id = ch_id;
        }

        public String getAcc_type() {
            return acc_type;
        }

        public void setAcc_type(String acc_type) {
            this.acc_type = acc_type;
        }

        public String getPrd_id() {
            return prd_id;
        }

        public void setPrd_id(String prd_id) {
            this.prd_id = prd_id;
        }
    }

    public static class RouteKey {

        private String pagy_id;

        private String ch_id;

        private String acc_type;

        private String prd_id;

        private Integer priority_id;

        public RouteKey() {
        }

        public RouteKey(String pagy_id, String ch_id, String acc_type, String prd_id, Integer priority_id) {
            this.pagy_id = pagy_id;
            this.ch_id = ch_id;
            this.acc_type = acc_type;
            this.prd_id = prd_id;
            this.priority_id = priority_id;
        }

        public String getPagy_id() {
            return pagy_id;
        }

        public void setPagy_id(String pagy_id) {
            this.pagy_id = pagy_id;
        }

        public String getCh_id() {
            return ch_id;
        }

        public void setCh_id(String ch_id) {
            this.ch_id = ch_id;
        }

        public String getAcc_type() {
            return acc_type;
        }

        public void setAcc_type(String acc_type) {
            this.acc_type = acc_type;
        }

        public String getPrd_id() {
            return prd_id;
        }

        public void setPrd_id(String prd_id) {
            this.prd_id = prd_id;
        }

        public Integer getPriority_id() {
            return priority_id;
        }

        public void setPriority_id(Integer priority_id) {
            this.priority_id = priority_id;
        }
    }


    @Test
    public void routePattern() {
        /*
          +-------+--------------+---------+-----------------+--------+
          | 渠道ID |    商户号    | 账户类型 |  产品id（交易类型）| 优先级 |
          +--------+-------------+---------+------------------+-------+
          | 312    | 20170907001 |  A005   |     PD001        |   1   |
          +--------+-------------+---------+------------------+-------+
          | 312    | *           |  A005   |     PD001        |   2   |
          +--------+-------------+---------+------------------+-------+
         */

        List<RouteKey> list = new ArrayList<>();

        RouteKey key1 = new RouteKey("312", "20170907001", "A005", "PD001", 1);
        RouteKey key2 = new RouteKey("312", "[0-9]{11}", "A005", "\\w{5}", 2);
        RouteKey key3 = new RouteKey("312", "[0-9]{11}", "\\w{4}", "\\w{5}", 3);

        list.add(key1);
        list.add(key2);
        list.add(key3);

        Param p = new Param("20170907001", "A005", "PD001");

        Map<Integer, String> ret = getRoute(list, p);

        for (Map.Entry<Integer, String> entry : ret.entrySet()) {
            log.info(entry.getKey() + "," + entry.getValue());
        }

    }


    private Map<Integer, String> getRoute(List<RouteKey> list, Param p) {
        Map<Integer, String> result = new TreeMap<>();

        for (RouteKey key : list) {

            {
                String ch_id = p.getCh_id();
                Pattern pattern = Pattern.compile(key.getCh_id());
                Matcher matcher = pattern.matcher(ch_id);
                if (!matcher.matches()) {
                    continue;
                }
            }

            {
                String acctype = p.getAcc_type();
                Pattern pattern = Pattern.compile(key.getAcc_type());
                Matcher matcher = pattern.matcher(acctype);
                if (!matcher.matches()) {
                    continue;
                }
            }

            {
                String prd_id = p.getPrd_id();
                Pattern pattern = Pattern.compile(key.getPrd_id());
                Matcher matcher = pattern.matcher(prd_id);
                if (matcher.matches()) {
                    result.put(key.getPriority_id(), key.getPagy_id());
                }
            }
        }

        return result;
    }

    @Test
    public void regexTest() {
        {
            String[] strs = {"PD1001", "PD1002", "PD1007", "PD1008"};
            String regex = "PD1001|PD1002|PD1007|PD1008";

            for (String s : strs) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(s);
                if (matcher.matches()) {
                    log.debug("[" + s + "] matches [" + regex + "]");
                } else {
                    log.debug("[" + s + "] not matched [" + regex + "]");
                }
            }
        }

        {
            String[] strs = {"A005", "A006", "A007"};
            String regex = "A005|A006|A007";

            for (String s : strs) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(s);
                if (matcher.matches()) {
                    log.debug("[" + s + "] matches [" + regex + "]");
                } else {
                    log.debug("[" + s + "] not matched [" + regex + "]");
                }
            }
        }
    }
}