package com.bob.cloud.netflix.apigateway.integration;

import com.bob.cloud.netflix.apigateway.integration.dto.BankDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "bankservice")
public interface BankClient {

    @RequestMapping(value = "/banks", method =
            RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BankDTO> allBanks();
}