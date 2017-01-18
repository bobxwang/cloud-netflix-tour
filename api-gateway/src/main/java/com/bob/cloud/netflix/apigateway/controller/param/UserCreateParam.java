package com.bob.cloud.netflix.apigateway.controller.param;

import com.bob.cloud.netflix.apigateway.constraint.Mobile;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserCreateParam {

    @NotNull
    @Mobile
    private String mobile;

}