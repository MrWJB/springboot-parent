package com.springboot.javascript.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Content implements Serializable {

    private String name;
    private String msg;
    private String type;
}
