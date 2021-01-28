package com.nongfu.kakax.basemain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nongfu.kakax.basemain.dao")
public class BaseMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseMainApplication.class, args);
    }

}
