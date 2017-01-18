package com.bob.cloud.netflix.apigateway.integration.hystrixImpl;

import com.bob.cloud.netflix.apigateway.integration.BankClient;
import com.bob.cloud.netflix.apigateway.integration.dto.BankDTO;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bankClientWithHystrix")
public class BankClientImplWithHystrix implements BankClient {

    /**
     * 采用Brixton.RC2版本后，注册进去的beanname不再是bankClient，还包括空间
     * 详见FeignClientsRegistrar类的registerFeignClient方法
     * 源码也可以通过别名来获得,别名就是后面加上FeignClient获得，跟Angel版本不一样
     */
    @Autowired
    @Qualifier("com.bob.cloud.netflix.apigateway.integration.BankClient")
//    @Qualifier("bankserviceFeignClient")
            BankClient bankClient;

    @Override
    @HystrixCommand(groupKey = "banks", fallbackMethod = "fallGetBanks", commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public List<BankDTO> allBanks() {

        return this.bankClient.allBanks();

        /**
         * https://github.com/Netflix/Hystrix/wiki/Configuration
         * we can see the config key from here,every key has a default value
         */
    }

    /**
     * this method should have the same input param and out param with the right method
     */
    public List<BankDTO> fallGetBanks() {
        return Lists.newArrayList();
    }
}