package com.bob.cloud.netflix.userservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntity {

    private long id;

    private String name;

    private int age;
}