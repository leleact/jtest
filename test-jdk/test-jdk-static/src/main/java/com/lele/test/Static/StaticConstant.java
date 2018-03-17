package com.lele.test.Static;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lele on 2017/7/15.
 */
public class StaticConstant {

    public static class StaticFiled {
        public static String filed = StaticMap.staticMap.get("AAA");
    }
}
