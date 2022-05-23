package com.example.springbootdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferRequest {
    private String provideStoreCode;
    private String demandStoreCode;
    private String expectArrivalTime = "2022-05-18 16:00:00";
    private String comment = "反向建调拨单(原因:物料禁用修复)";
    private List<TransferItemRequest> items;
}
