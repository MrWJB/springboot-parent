package com.springboot.javascript.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;

@Data
public class Sale implements Serializable {

    @JsonAlias("saleName")
    private String sale_name;
    @JsonAlias("salePrice")
    private String sale_price;
    @JsonAlias("saleAddr")
    private String sale_addr;
}
