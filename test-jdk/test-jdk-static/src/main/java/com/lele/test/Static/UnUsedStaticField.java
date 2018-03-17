package com.lele.test.Static;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Lele on 2017/7/10.
 */
public class UnUsedStaticField {

    private static Logger log = LoggerFactory.getLogger(UnUsedStaticField.class);

    private static String field = "YYYY";

    static {
        log.debug("staic block begin");
        log.debug(field);
        log.debug("static block end");
    }
}
