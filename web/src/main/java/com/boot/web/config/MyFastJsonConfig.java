package com.boot.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

@Configuration
public class MyFastJsonConfig {

    @Bean
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd");
        config.setCharset(Charset.forName("UTF-8"));
        config.setSerializerFeatures(
                //是否在生成的json中输出类名
                SerializerFeature.WriteClassName,
                //是否在生成的json中输出value为null的数据。
                SerializerFeature.WriteMapNullValue,
                //格式化
                SerializerFeature.PrettyFormat,
                //空集合输出[]而非null
                SerializerFeature.WriteNullListAsEmpty,
                //空字符串输出“”而非null等基本配置。
                SerializerFeature.WriteNullStringAsEmpty
        );
        converter.setFastJsonConfig(config);
        return converter;
    }
}
