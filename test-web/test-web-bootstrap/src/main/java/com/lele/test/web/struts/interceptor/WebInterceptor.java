package com.lele.test.web.struts.interceptor;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Lele on 2017/3/25.
 */
public class WebInterceptor implements Interceptor {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void destroy() {
        log.trace("");
    }

    @Override
    public void init() {
        log.trace("");
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        log.trace("");
        return null;
    }
}
