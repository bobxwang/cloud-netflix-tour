package com.bob.cloud.netflix.bankservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringCloudApplication
@EnableFeignClients
@RestController
public class BankServiceApp {

    public static void main(String[] args) {

        SpringApplication.run(BankServiceApp.class, args);
    }

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/onlineservice", method =
            RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceInstance> onlineservice() {
        List<ServiceInstance> services = discoveryClient.getInstances("RISK-OPENAPI");
        return services;
    }
}