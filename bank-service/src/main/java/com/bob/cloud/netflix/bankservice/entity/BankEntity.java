package com.bob.cloud.netflix.bankservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankEntity {

    private long id;

    private String name;

    private String address;
}