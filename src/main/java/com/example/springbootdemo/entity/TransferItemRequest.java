package com.example.springbootdemo.entity;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferItemRequest {
    private String code;
    private BigDecimal requiredQuantity;
    private String reason;
}
