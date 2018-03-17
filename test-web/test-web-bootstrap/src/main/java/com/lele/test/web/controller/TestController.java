package com.lele.test.web.controller;

import com.lele.test.web.service.AutoWiredService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lele on 2017/3/12.
 */
@Controller
public class TestController {

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private AutoWiredService autoWiredService;

    @RequestMapping(value = "/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.trace("step into Controller");

        String result = IOUtils.toString(request.getInputStream(), "UTF-8");

        response.getOutputStream().write(result.getBytes("UTF-8"));

        log.trace("step out Controller");
    }

    @RequestMapping(value = "/autoWiredTester")
    public void autoWiredServiceTester(HttpServletRequest request, HttpServletResponse response) throws IOException {
        autoWiredService.doSomeThing();

        response.getOutputStream().write("autoWiredTester 应答正常".getBytes("UTF-8"));
    }
}
