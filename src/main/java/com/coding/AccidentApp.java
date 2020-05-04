package com.coding;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableAsync
@EnableSwagger2Doc
@SpringBootApplication
public class AccidentApp {
    public static void main(String[] args) {
        SpringApplication.run(AccidentApp.class, args);
    }

}
