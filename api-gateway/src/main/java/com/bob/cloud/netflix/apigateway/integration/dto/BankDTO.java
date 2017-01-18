package com.bob.cloud.netflix.apigateway.integration.dto;

import lombok.Data;

/**
 * 银行实体
 */
@Data
public class BankDTO {
    private long id;
    private String name;
    private String address;
}