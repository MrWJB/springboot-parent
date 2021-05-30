package com.boot.web.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义Error数据
 */
//@Component
public class MyDefaultErrorAttributes extends DefaultErrorAttributes {

    /**
     * 自定义Error数据就是对返回的数据进行自定义。
     * springboot返回的error信息一共有5条，分别是timestamp，status，error，message以及path；
     * @param webRequest
     * @param options
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        map.put("custommsg","出错了！！");
        map.remove("error");
        return map;
    }
}
