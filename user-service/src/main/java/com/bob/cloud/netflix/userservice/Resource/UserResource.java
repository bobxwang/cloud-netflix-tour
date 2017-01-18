package com.bob.cloud.netflix.userservice.Resource;

import com.bob.cloud.netflix.userservice.Entity.UserEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * 实际这个可以单独写成一个jar包，提供给调用方
 */
@FeignClient("user-service")
public interface UserResource {

    @RequestMapping(value = "/users", method =
            RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserEntity>> allUsers();

    @RequestMapping(value = "/users/properties",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Map listAllProperty();
}