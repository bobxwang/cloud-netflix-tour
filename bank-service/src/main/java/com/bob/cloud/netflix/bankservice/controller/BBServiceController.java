package com.bob.cloud.netflix.bankservice.controller;

import com.bob.feign.inheritance.BBService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BBServiceController implements BBService {

    @Override
    public Map<String, String> getMap(@PathVariable("id") long id) {
        Map<String, String> map = new HashMap();
        map.put("id", String.valueOf(id));
        return map;
    }
}