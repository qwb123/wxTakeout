package com.qwb.takeout;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qwb.takeout.dao")
public class TakeoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakeoutApplication.class, args);
    }
}
