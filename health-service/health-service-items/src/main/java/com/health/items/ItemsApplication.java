package com.health.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.health.items.dao")
@EnableScheduling
public class ItemsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemsApplication.class, args);
    }
}
