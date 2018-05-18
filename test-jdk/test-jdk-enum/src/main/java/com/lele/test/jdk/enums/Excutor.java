package com.lele.test.jdk.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Lele on 2017/7/7.
 */
public class Excutor {

    private static Logger log = LoggerFactory.getLogger(Excutor.class);

    private EType mEType;

    public EType getmEType() {
        return mEType;
    }

    public void setmEType(EType mEType) {
        this.mEType = mEType;
    }

    public Excutor(EType e) {
        this.mEType = e;
    }

    public void method() {
        log.debug("" + mEType.value());
    }
}
