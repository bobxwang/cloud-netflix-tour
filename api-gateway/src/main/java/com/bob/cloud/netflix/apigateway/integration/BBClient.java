package com.bob.cloud.netflix.apigateway.integration;

import com.bob.feign.inheritance.BBService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "bankservice")
public interface BBClient extends BBService {

}