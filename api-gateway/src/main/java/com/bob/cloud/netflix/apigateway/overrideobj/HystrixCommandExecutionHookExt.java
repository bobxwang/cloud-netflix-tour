package com.bob.cloud.netflix.apigateway.overrideobj;

import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHookDefault;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HystrixCommandExecutionHookExt extends HystrixCommandExecutionHook {

    private HystrixCommandExecutionHook instance = HystrixCommandExecutionHookDefault.getInstance();

    @Autowired
    OkHttpClient okHttpClient;

    @PostConstruct
    public void init() {
        HystrixPlugins.getInstance().registerCommandExecutionHook(this);
    }

    @Override
    public <T> Exception onError(HystrixInvokable<T> commandInstance, HystrixRuntimeException.FailureType failureType, Exception e) {
        return super.onError(commandInstance, failureType, e);
    }

    @Override
    public <T> void onFallbackSuccess(HystrixInvokable<T> commandInstance) {
        super.onFallbackSuccess(commandInstance);
    }
}