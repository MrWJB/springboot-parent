package com.springboot.javascript.controller;

import com.springboot.javascript.domain.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RequestController {

    private Logger log = LoggerFactory.getLogger("RequestController");

    @PostMapping(value = "/req",produces = "application/json;charset=utf8")
    public String request(@RequestBody Request req){
        log.info("req:>>" + req.toString());
        return  req.toString();
    }
}
