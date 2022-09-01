package com.zy.springbootsse;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableRabbit
@EnableDiscoveryClient
public class SpringbootSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSseApplication.class, args);
    }

}
