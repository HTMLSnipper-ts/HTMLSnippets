package com.example.CodeSnippetLambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.CodeSnippetLambda.controller.PingController;


@SpringBootApplication
// We use direct @Import instead of @ComponentScan to speed up cold starts
// @ComponentScan(basePackages = "com.example.controller")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
