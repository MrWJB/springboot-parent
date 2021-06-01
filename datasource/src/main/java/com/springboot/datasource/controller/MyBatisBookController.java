package com.springboot.datasource.controller;

import com.springboot.datasource.mapper1.BookMapper;
import com.springboot.datasource.mapper2.BookMapper2;
import com.springboot.datasource.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyBatisBookController {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookMapper2 bookMapper2;

    @GetMapping("/test1")
    public void test1(){
        List<Book> books1 = bookMapper.getAllBooks();
        List<Book> books2 = bookMapper2.getAllBooks();
        System.out.println(books1);
        System.out.println(books2);
    }
}
