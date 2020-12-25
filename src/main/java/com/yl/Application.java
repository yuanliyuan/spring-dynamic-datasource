package com.yl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 同一台电脑同时配置gitLab和github
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.yl")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("启动了没？");
    }

}
