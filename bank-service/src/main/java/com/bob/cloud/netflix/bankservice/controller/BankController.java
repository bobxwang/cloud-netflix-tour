package com.bob.cloud.netflix.bankservice.controller;

import com.bob.cloud.netflix.bankservice.entity.BankEntity;
import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {

    @RequestMapping(value = "/banks", method =
            RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BankEntity> allBanks() {

        List<BankEntity> results = Lists.newArrayList();
        results.add(new BankEntity(1, "abc", "1"));
        results.add(new BankEntity(2, "def", "2"));
        results.add(new BankEntity(3, "ghi", "3"));
        return results;
    }
}