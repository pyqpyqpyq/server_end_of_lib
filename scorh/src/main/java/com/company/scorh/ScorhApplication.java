package com.company.scorh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.company.scorh.dao")
public class ScorhApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScorhApplication.class, args);
    }

}
