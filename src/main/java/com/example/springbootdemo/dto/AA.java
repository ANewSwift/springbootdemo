package com.example.springbootdemo.dto;

import java.util.Optional;

public class AA {
    public static void main(String[] args) {
        Object o = Optional.ofNullable(null).orElse("bb");
        System.out.println(o);
    }
}
