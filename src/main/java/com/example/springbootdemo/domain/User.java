package com.example.springbootdemo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private long id;
    private String name;
    private short age;
}
