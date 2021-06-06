package com.springboot.javascript.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseBody implements Serializable {

    private String resCode;
    private String resMsg;
    private Content content;
}
