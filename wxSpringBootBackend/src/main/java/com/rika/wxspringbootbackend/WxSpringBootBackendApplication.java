package com.rika.wxspringbootbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.rika.wxspringbootbackend.mapper")
public class WxSpringBootBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxSpringBootBackendApplication.class, args);
    }

}
