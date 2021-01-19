package com.example.webforandroid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/example/webforandroid/mapper")
public class WebforandroidApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebforandroidApplication.class, args);
    }

}
