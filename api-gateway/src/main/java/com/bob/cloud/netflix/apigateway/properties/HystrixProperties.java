package com.bob.cloud.netflix.apigateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hystrix")
@Data
public class HystrixProperties {

    private boolean enabled;

    private boolean streamEnabled;

    private String streamUrl;
}