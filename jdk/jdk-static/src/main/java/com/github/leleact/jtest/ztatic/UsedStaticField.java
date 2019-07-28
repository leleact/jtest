package com.github.leleact.jtest.ztatic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Lele on 2017/7/10.
 */
public class UsedStaticField {

    private static Logger log = LoggerFactory.getLogger(UsedStaticField.class);

    public static String field = "XXXX";

    static {
        log.debug("static block begin");
        log.debug(field);
        log.debug("static block end");
    }
}
