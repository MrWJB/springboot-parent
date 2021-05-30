package com.boot.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BannerApplication.class, args);

//        SpringApplicationBuilder builder = new SpringApplicationBuilder(BannerApplication.class);
//        builder.bannerMode(Banner.Mode.OFF).run(args);
    }

}
