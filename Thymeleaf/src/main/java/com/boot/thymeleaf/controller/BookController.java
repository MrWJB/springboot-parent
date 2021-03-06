package com.boot.thymeleaf.controller;

import com.boot.thymeleaf.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @GetMapping("/books")
    public ModelAndView books(){
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("罗贯中");
        book1.setAuthor("三国演义");
        Book book2 = new Book();
        book2.setId(2);
        book2.setName("曹雪芹");
        book2.setAuthor("红楼梦");
        books.add(book1);
        books.add(book2);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("books");
        mv.addObject("books",books);
        return mv;
    }

}
