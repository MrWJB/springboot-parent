package com.boot.web.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView uploadException(MaxUploadSizeExceededException e, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.write("上传文件大小超出限制！");
//        out.flush();
//        out.close();
        //如果返回参数是一个ModelAndView,假设使用的页面模板为Thymeleaf（注意添加Thymeleaf相关依赖），此时异常处理方法定义如下：
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","上传文件大小超出限制!");
        mv.setViewName("error");
        return mv;
    }

    /**
     * 添加全局数据
     * 该方法有一个@ModelAttribute注解，其中的value属性表示这条返回数据的key，而方法的返回值是返回数据的value。
     * 此时在任意请求的Controller中，通过方法参数中的Model都可以获取info的数据。
     */
    @ModelAttribute(value = "info")
    public Map<String,String> userInfo(){
        HashMap<String,String> map = new HashMap<>();
        map.put("username","罗贯中");
        map.put("gender","男");
        return map;
    }

    /**
     *请求参数预处理
     * 第一个@InitBinder("b")表示该方法是处理@ModelAttribute("b")对应的参数的，第二个 @InitBinder("a")
     * 表示该方法时处理@ModelAttribute("a")对应的参数的。
     * 在每个方法中给相应的Field设置一个前缀，然后在浏览器中请求https://localhost:8081/chapter02/books?b.name=三国演义&b.author=罗贯中&a.name=曹雪芹&a.age=23
     * 即可成功区分出name属性。
     * 在WebDataBinder对象中，还可以设置允许的字段，禁止的字段，必填的字段以及验证器等。
     */
    @InitBinder("b")
    public void init(WebDataBinder binder){
        binder.setFieldDefaultPrefix("b.");
    }
    @InitBinder("a")
    public void init2(WebDataBinder binder){
        binder.setFieldDefaultPrefix("a.");
    }
}
