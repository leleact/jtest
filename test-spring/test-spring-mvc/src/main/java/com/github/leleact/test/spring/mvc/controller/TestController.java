package com.github.leleact.test.spring.mvc.controller;

import com.github.leleact.test.spring.mvc.bean.request.CommonRequest;
import com.github.leleact.test.spring.mvc.bean.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
public class TestController {

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/test")
    public void test(HttpServletRequest request, HttpServletResponse response) {

        log.debug(request.toString());

        log.debug("entry...");

        log.debug(response.toString());
    }

    @ResponseBody
    @RequestMapping("/by")
    public byte[] getByte(@RequestBody String b) throws UnsupportedEncodingException {
        return b.getBytes("UTF-8");
    }

    @ResponseBody
    @RequestMapping(value = "/hand", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse handler(@RequestBody CommonRequest request) {
        CommonResponse response = new CommonResponse();
        response.setRespCode("0000");
        response.setRespMsg(request.toString());
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/retstr")
    public String stringValRet() {
        return "string value";
    }


    @ResponseBody
    @RequestMapping(value = "/block")
    public String block(@RequestBody CommonRequest request) {
        log.info("block begin");
        try {
            Thread.sleep(3600000L);
        } catch (InterruptedException e) {
            log.info("Interrupted", e);
            Thread.currentThread().interrupt();
        }
        log.info("block end");
        return "string value";
    }
}
