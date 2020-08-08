package com.jsu.it;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.jsu.it.dao")
public class ItApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItApplication.class, args);
    }

}
