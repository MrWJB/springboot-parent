package com.springboot.javascript.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AJaxController {

    private Logger log = LoggerFactory.getLogger("AJaxController");

    @GetMapping("/ajax")
    @CrossOrigin
    public String getAjaxRequest(HttpServletRequest request){
        log.info("执行Ajax请求");
        return null;
    }
}
