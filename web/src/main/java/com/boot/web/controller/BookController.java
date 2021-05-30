package com.boot.web.controller;

import com.boot.web.domain.Authors;
import com.boot.web.domain.Book;
import com.boot.web.domain.Books;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/book")
public class BookController {

    @GetMapping("/book")
    public Book book(){
        Book book = new Book();
        book.setAuthor("罗贯中");
        book.setName("三国演义");
        book.setPrice(30f);
        book.setPublicationDate(new Date());
        return book;
    }

    /**
     * 获取全局数据 全局数据在CustomExceptionHandler中配置了（注：类上标注了@ControllerAdvice注解）
     * @param model
     */
    @GetMapping("/hello")
    public void hello(Model model){
        Map<String, Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Object value = map.get(key);
            System.out.println(key + ">>>>>>>>" +value);
        }
    }

    /**
     * 请求参数预处理
     * 此时在参数传递时，两个实体类中的name属性会混淆，@ControllerAdivce结合@InitBinder可以顺利解决该问题。
     * 首先在Controller中方法的参数添加@ModelAttribute注解。然后再配置@ControllerAdivce
     *
     */
    @GetMapping("/books")
    public String books(@ModelAttribute("b") Books books, @ModelAttribute("a") Authors authors){
        return books.toString() + ">>>>>" + authors.toString();
    }

    /**
     * 配置跨域 方法级别；全局方式在MyWebMvcConfig中配置
     * @CrossOrigin 中的value表示支持的域，这里表示来自http://localhost:8081域的请求是支持跨域的
     * maxAge表示探测请求的有效期，对于DELETE\PUT请求或者有自定义头信息的请求，在执行过程中会先发送探测请求，探测请求不用每次
     * 都发送，可以配置一个有效期，有效期过了之后才会方送探测请求。这个属性默认是1800秒，即30分钟。
     * allowedHeaders表示允许的请求头，*表示所有的请求头都被允许。
     */
    @PostMapping("/book")
    @CrossOrigin(value = "http://localhost:8081",maxAge = 1800,allowedHeaders = "*")
    public String addBook(String name){
        return "receive:" + name;
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(value = "http://localhost:8081",maxAge = 1800,allowedHeaders = "*")
    public String deleteBookById(@PathVariable Long id){
        return String.valueOf(id);
    }
}
