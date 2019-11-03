package com.cby;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.cby.*.mapper")
@ComponentScan("com.cby")
public class SpringDemoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoWebApplication.class, args);
    }

}
