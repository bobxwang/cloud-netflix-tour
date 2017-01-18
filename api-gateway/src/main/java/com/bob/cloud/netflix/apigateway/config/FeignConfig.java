package com.bob.cloud.netflix.apigateway.config;

import com.bob.cloud.netflix.apigateway.overrideobj.FeignErrorDecoder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class FeignConfig {

    @Bean
    public RequestInterceptor requestTokenInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {

                RequestAttributes ra = RequestContextHolder.getRequestAttributes();
                if (ra != null) {
                    HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
                    request.getHeader("AuthorizationOne");
                    requestTemplate.header("AuthorizationOne", "userId + token");
                }
            }
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
}