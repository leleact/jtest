package com.lele.test.web.struts.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimerInterceptor extends AbstractInterceptor {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        long start = System.currentTimeMillis();
        String result = invocation.invoke();
        long end = System.currentTimeMillis();
        log.debug("time:" + (end - start) + "ms");
        return result;
    }
}
