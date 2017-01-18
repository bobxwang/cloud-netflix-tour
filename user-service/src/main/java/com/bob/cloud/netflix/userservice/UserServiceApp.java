package com.bob.cloud.netflix.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringCloudApplication
@EnableFeignClients
@EnableEurekaClient
public class UserServiceApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(UserServiceApp.class, args);
        System.out.println(configurableApplicationContext.getApplicationName());
    }
}