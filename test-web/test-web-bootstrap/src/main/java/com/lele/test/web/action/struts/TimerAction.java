package com.lele.test.web.action.struts;

import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Lele on 2017/3/25.
 */
public class TimerAction extends ActionSupport {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String execute() throws Exception {
        for (int i = 0; i < 10000; i++) {
            log.debug("counter:" + i);
        }
        return SUCCESS;
    }
}
