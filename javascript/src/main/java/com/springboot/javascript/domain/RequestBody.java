package com.springboot.javascript.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RequestBody implements Serializable {

    private String prov;
    private String city;
    private String mobile;
    private String name;
    private Double price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
    private Sale sale;
}
