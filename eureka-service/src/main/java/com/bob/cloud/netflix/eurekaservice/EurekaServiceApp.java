package com.bob.cloud.netflix.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务发现入口
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApp {

    public static void main(String[] args) {

        SpringApplication.run(EurekaServiceApp.class, args);
    }
}