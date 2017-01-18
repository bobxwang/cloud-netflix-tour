package com.bob.cloud.netflix.apigateway.integration;

import com.bob.cloud.netflix.apigateway.integration.dto.UserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "userservice")
public interface UserClient {

    @RequestMapping(value = "/users", method =
            RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDTO>> getAllUsers();
}