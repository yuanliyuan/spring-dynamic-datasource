package com.yl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * 精准平台--消息队列--生产者
 * @author yuanli 
 * @date 2018年10月31日 上午9:17:23
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.yl")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("启动了没？");
	}

}
