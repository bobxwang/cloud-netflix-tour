package com.bob.cloud.netflix.apigateway.controller;

import com.bob.cloud.netflix.apigateway.integration.BBClient;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class OnlineServiceController {

    @Autowired
    DiscoveryClient discoveryClient;

    AtomicInteger atomicInteger = new AtomicInteger();

    @Autowired
    BBClient bbClient;

    @HystrixCommand(groupKey = "online", commandKey = "disservice", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "600000")
    })
    @RequestMapping(value = "services", method = RequestMethod.GET)
    public List<String> Services() {

        Integer i = atomicInteger.getAndIncrement();
        if (i % 5 == 0) {
            throw new RuntimeException("i now is: " + i);
        }

        List<String> result = Lists.newArrayList();
        List<String> services = discoveryClient.getServices();
        result.addAll(services);
        for (String service : services) {
            List<ServiceInstance> list = discoveryClient.getInstances(service);
            if (list.size() > 0) {
                result.add(list.get(0).getUri().toString());
            }
        }
        return result;
    }

    @HystrixCommand(groupKey = "online", commandKey = "disservice", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "600000")
    })
    @RequestMapping(value = "bbservices", method = RequestMethod.GET)
    public Map<String, String> BBServices() {

        Map<String, String> map = bbClient.getMap(12);
        map.put("bb", "bb");
        return map;
    }
}