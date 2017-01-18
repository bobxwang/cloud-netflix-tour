package com.bob.cloud.netflix.userservice.Resource.Impl;

import com.bob.cloud.netflix.userservice.Entity.UserEntity;
import com.bob.cloud.netflix.userservice.Resource.UserResource;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserResourceImpl implements UserResource {

    @Value("${user.access.url}")
    private String url;
    @Value("${user.access.pwd}")
    private String pwd;

    @Override
    public ResponseEntity<List<UserEntity>> allUsers() {

        List<UserEntity> results = Lists.newArrayList();
        results.add(new UserEntity(1, "abc", 1));
        results.add(new UserEntity(2, "def", 2));
        results.add(new UserEntity(3, "ghi", 3));
        return new ResponseEntity(results, HttpStatus.OK);
    }

    @Override
    public Map listAllProperty() {
        Map map = Maps.newHashMap();
        map.put("url", url);
        map.put("pwd", pwd);
        return map;
    }
}