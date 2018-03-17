package com.lele.test.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lele on 2017/3/13.
 */
@Controller
@RequestMapping("/quickpay")
public class QuickPay {

    private static Logger log = LoggerFactory.getLogger(QuickPay.class);

    @RequestMapping("/input")
    public ModelAndView inputAction(Model model, HttpServletRequest request, HttpServletResponse response) {
        log.debug("Step into /quickpay/input");
        ModelAndView mv = new ModelAndView("quickpay");
        log.debug("Step out /quickpay/input");
        return mv;
    }

    @RequestMapping("/submit")
    public ModelAndView submitAction(Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("quickpay");
        return mv;
    }
}
