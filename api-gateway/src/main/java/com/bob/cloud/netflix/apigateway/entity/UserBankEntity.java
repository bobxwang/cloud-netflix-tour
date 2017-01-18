package com.bob.cloud.netflix.apigateway.entity;

import com.bob.cloud.netflix.apigateway.integration.dto.BankDTO;
import lombok.Data;

import java.util.List;

/**
 * 用户银行实体
 */
@Data
public class UserBankEntity {

    private long userId;

    private String userName;

    private List<BankDTO> banks;
}