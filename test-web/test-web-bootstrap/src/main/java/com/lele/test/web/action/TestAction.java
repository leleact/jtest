package com.lele.test.web.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Lele on 2017/3/23.
 */
public class TestAction extends ActionSupport {

    public static final String MESSAGE = "Struts is up and running ...";

    public String execute() throws Exception {
        setMessage(MESSAGE);
        return SUCCESS;
    }

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
