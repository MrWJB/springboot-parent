package com.springboot.javascript.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;

@Data
public class Request implements Serializable {

    @JsonAlias("header")
    private RequestHeader header;

    @JsonAlias("body")
    private RequestBody body;
}
