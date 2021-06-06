package com.springboot.javascript.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {

    @JsonAlias("header")
    private ResponseHeader header;

    @JsonAlias("body")
    private ResponseBody body;

}
