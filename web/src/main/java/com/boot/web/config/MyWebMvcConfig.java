package com.boot.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.boot.web.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 两种实现方式：
 * 方式一：配置 MyFastJsonConfig
 * 方式二：当前方式
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
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
        converters.add(converter);
    }

    /**
     * 添加静态资源配置信息
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//    }

    /**
     * 全局方式
     * 配置CORS跨域
     */
//    public void addCorsMapping(CorsRegistry corsRegistry){
//        corsRegistry.addMapping("/book/**")
//                .allowedHeaders("*")
//                .allowedMethods("*")
//                .maxAge(1800)
//                .allowedOrigins("http://localhost:8081");
//    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/hello");
    }

    /**
     * 路径映射
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("index").setViewName("index");
    }
}
