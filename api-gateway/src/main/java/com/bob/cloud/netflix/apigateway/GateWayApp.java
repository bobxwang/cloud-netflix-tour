package com.bob.cloud.netflix.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringCloudApplication
@EnableFeignClients
@EnableHystrix
public class GateWayApp {

    public static void main(String[] args) {

//        UserCreateParam param = new UserCreateParam();
//        try {
//            BeanValidator.validate(param);
//        } catch (Exception e) {
//            System.out.printf(e.getMessage());
//        }
//        param.setMobile("13608973456");
//        try {
//            BeanValidator.validate(param);
//        } catch (Exception e) {
//            System.out.printf(e.getMessage());
//        }

        SpringApplication.run(GateWayApp.class, args);
    }
}