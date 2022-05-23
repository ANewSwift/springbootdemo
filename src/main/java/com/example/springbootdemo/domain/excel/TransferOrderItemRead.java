package com.example.springbootdemo.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferOrderItemRead {
    private String code;
    private String provide_k3store_code;
    private String demand_k3store_code;
    private String material_code;
    private BigDecimal required_quantity;
    private String reason;
}
