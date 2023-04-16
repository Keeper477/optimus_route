package com.prok.optimusroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OptimizerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OptimizerApplication.class, args);
    }
}