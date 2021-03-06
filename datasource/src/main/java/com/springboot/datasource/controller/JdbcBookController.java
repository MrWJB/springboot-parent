package com.springboot.datasource.controller;

import com.springboot.datasource.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sang on 2018/7/16.
 */
@RestController
public class JdbcBookController {

    @Resource(name = "jdbcTemplateOne")
//    @Autowired
    JdbcTemplate jdbcTemplate;

    @Resource
    @Qualifier("jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo;

    @GetMapping("/testJdbc")
    public void testJdbc() {
        List<Book> books1 = jdbcTemplate.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));
        List<Book> books2 = jdbcTemplateTwo.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));
        System.out.println("books1:"+books1);
        System.out.println("books2:"+books2);
    }
}
