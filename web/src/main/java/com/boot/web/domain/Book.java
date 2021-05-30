package com.boot.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Book {
    private String name;
    private String author;
    @JsonIgnore
    private Float price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;
}
