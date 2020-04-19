package com.example.dramaserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dramaserver.dao")
public class DramaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DramaServerApplication.class, args);
    }

}
