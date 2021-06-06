package com.springboot.javascript.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseHeader implements Serializable {

    private String time;
    private String version;
    private String code;
}
