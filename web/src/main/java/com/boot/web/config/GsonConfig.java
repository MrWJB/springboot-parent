package com.boot.web.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Modifier;

@Configuration
public class GsonConfig {

    /**
     *
     * @return
     */
    @Bean
    GsonHttpMessageConverter gsonHttpMessageConverter(){
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        //设置Gson解析时修饰符为protected的字段被过滤掉。
        gsonBuilder.excludeFieldsWithModifiers(Modifier.PROTECTED);
        Gson gson = gsonBuilder.create();
        converter.setGson(gson);
        return converter;
    }
}
