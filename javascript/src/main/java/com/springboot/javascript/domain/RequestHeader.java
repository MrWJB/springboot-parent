package com.springboot.javascript.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RequestHeader {

    private String alice;
    private String version;
    private String code;
}
